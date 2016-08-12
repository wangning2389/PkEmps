package module.common.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.Component;
import com.PublicSystem;
import core.exception.BusinessException;

@Controller
@RequestMapping("/module/common.do")
public class UploadFileAction
{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 7566364916209313775L;
    
    private File Filedata;
    
    private String imgFileContentType;
    
    int i = 0;
    
    HttpServletResponse response;
    
    PublicSystem sys = PublicSystem.getInstance();
    
    public String getImgFileContentType()
    {
        return imgFileContentType;
    }
    
    public void setImgFileContentType(String imgFileContentType)
    {
        this.imgFileContentType = imgFileContentType;
    }
    
    public File getFiledata()
    {
        return Filedata;
    }
    
    public void setFiledata(File filedata)
    {
        Filedata = filedata;
    }
    
    @RequestMapping(params = "method=uploadimg")
    public void uploadimg(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        
        MultipartFile imgFile1 = multipartRequest.getFile("photo");
        // 定义一个数组，用于保存可上传的文件类型
        List fileTypes = new ArrayList();
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("bmp");
        fileTypes.add("gif");
        fileTypes.add("png");
        
        String src = request.getRealPath("upload");
        
        if (!(imgFile1.getOriginalFilename() == null || "".equals(imgFile1.getOriginalFilename())))
        {
            /*
             * 下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名 自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等
             */
            File file1 = this.getFile(imgFile1, src, fileTypes);
            String name = file1.getName();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            String json = jsonObject.toString();
            Component.print(json, response);
        }
        
    }
    
    /**
     * 通过传入页面读取到的文件，处理后保存到本地磁盘，并返回一个已经创建好的File
     * 
     * @param imgFile 从页面中读取到的文件
     * @return
     * @throws IOException
     */
    private File getFile(MultipartFile imgFile, String src, List fileTypes)
        throws IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");// 以当前精确到秒的日期为上传的文件的文件名
        String fileName = sdf.format(new Date()) + i++;// +imgFile.getOriginalFilename();
        // 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
        String ext = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf(".") + 1,
            imgFile.getOriginalFilename().length());
        // 对扩展名进行小写转换
        ext = ext.toLowerCase();
        
        File file = null;
        if (fileTypes.contains(ext))
        { // 如果扩展名属于允许上传的类型，则创建文件
            file = new File(src + File.separator + fileName);
            try
            {
                imgFile.transferTo(file); // 保存上传的文件
            }
            catch (IllegalStateException e)
            {
                e.printStackTrace();
                throw e;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                throw e;
            }
        }
        else
        {
            throw new BusinessException("照片保存失败！");
            
        }
        return file;
    }
    
    /**
     * 检测与创建一级、二级文件夹、文件名 这里我通过传入的两个字符串来做一级文件夹和二级文件夹名称 通过此种办法我们可以做到根据用户的选择保存到相应的文件夹下
     */
    /*
     * private File creatFolder(String typeName, String brandName, String fileName) { File file = null; typeName =
     * typeName.replaceAll("/", ""); // 去掉"/" typeName = typeName.replaceAll(" ", ""); // 替换半角空格 typeName =
     * typeName.replaceAll(" ", ""); // 替换全角空格 brandName = brandName.replaceAll("/", ""); // 去掉"/" brandName =
     * brandName.replaceAll(" ", ""); // 替换半角空格 brandName = brandName.replaceAll(" ", ""); // 替换全角空格 File firstFolder =
     * new File(typeName); // 一级文件夹 if (firstFolder.exists()) { // 如果一级文件夹存在，则检测二级文件夹 File secondFolder = new
     * File(firstFolder, brandName); if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件 file = new File(secondFolder,
     * fileName); } else { // 如果二级文件夹不存在，则创建二级文件夹 secondFolder.mkdir(); file = new File(secondFolder, fileName); //
     * 创建完二级文件夹后，再合建文件 } } else { // 如果一级不存在，则创建一级文件夹 firstFolder.mkdir(); File secondFolder = new File(firstFolder,
     * brandName); if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件 file = new File(secondFolder, fileName); } else { //
     * 如果二级文件夹不存在，则创建二级文件夹 secondFolder.mkdir(); file = new File(secondFolder, fileName); } } return file; }
     */

}