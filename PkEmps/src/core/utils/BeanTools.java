package core.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

/**
 * @author xieguojun
 * @version $Revision$ Jun 3, 2010
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class BeanTools extends BeanUtils {

	/**
	 * 复制某个对象为目标对象类型的对象
	 * 
	 * @param <T> 目标对象类型参数
	 * @param source 源对象
	 * @param destType 目标对象类型
	 * @return
	 */
	public static <T> T copyAs(Object source, Class<T> destType) {
		if (source == null) {
			return null;
		}
		try {
			T dest = destType.newInstance();
			BeanUtils.copyProperties(source, dest);
			return dest;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 复制源对象集合到目标对象列表
	 * 
	 * @param <T>
	 * @param source
	 * @param destType
	 * @return
	 */
	public static <T> List<T> copyAs(Collection<?> source, Class<T> destType) {
		if (source == null) {
			return null;
		}
		ArrayList<T> result = new ArrayList<T>();
		for (Object object : source) {
			result.add(copyAs(object, destType));
		}
		return result;
	}

	/**
	 * 拷贝时，忽略属性
	 */
	public static final String[] IGNORE_PROPS = new String[] { "", "" };

	/**
	 * @author xieguojun 拷贝属性
	 * @param source
	 * @param target
	 * @param ignore 是否忽略BeanUtils.IGNORE_PROPS的字段
	 * @throws BeansException
	 */
	public static void copyProperties(Object source, Object target, boolean ignore) throws BeansException {
		if (ignore)
			copyProperties(source, target, IGNORE_PROPS);
		else
			copyProperties(source, target);
	}

	/**
	 * @author xieguojun 拷贝属性，忽略元数据空值复制
	 * @param source
	 * @param target
	 * @param ignore 是否忽略BeanUtils.IGNORE_PROPS的字段
	 * @throws BeansException
	 */
	public static void copyPropertiesIgnoreNull(Object source, Object target, boolean ignore) throws BeansException {
		_copyProperties(source, target, ignore, true);
	}

	/**
	 * @author xieguojun
	 * @param source
	 * @param target
	 * @param ignore 是否忽略属性IGNORE_PROPS复制
	 * @param ignoreNull 是否忽略空值复制
	 * @throws BeansException
	 */
	private static void _copyProperties(Object source, Object target, boolean ignore, boolean ignoreNull)
			throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		PropertyDescriptor[] targetPds = getPropertyDescriptors(target.getClass());
		List ignoreList = ignore ? null : Arrays.asList(IGNORE_PROPS);

		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			if (targetPd.getWriteMethod() != null && (ignore || (!ignoreList.contains(targetPd.getName())))) {

				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method getter = sourcePd.getReadMethod();
						if (!Modifier.isPublic(getter.getDeclaringClass().getModifiers())) {
							getter.setAccessible(true);
						}
						Object value = getter.invoke(source, new Object[0]);

						if (ignoreNull && value == null)
							continue;

						Method setter = targetPd.getWriteMethod();
						if (!Modifier.isPublic(setter.getDeclaringClass().getModifiers())) {
							setter.setAccessible(true);
						}
						setter.invoke(target, new Object[] { value });
					}
					catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}
}
