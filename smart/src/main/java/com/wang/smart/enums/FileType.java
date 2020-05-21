package com.wang.smart.enums;

/**
 * 文件类型
 * @author lwh
 * @date 2017年8月29日 上午11:57:33
 */
public enum FileType  {
	FILE(0,"文件","/upload/",new String[]{}),
	IMAGE(1,"图片","/img/",new String[]{"png","jpg","jpeg","PNG","JPG","JPEG"}),
	VIDEO(2,"视频","/video/",new String[]{"mp4","avi","flv","MP4","AVI","FLV"}),
	MUSIC(3,"音乐","/music/",new String[]{"mp3","MP3"}),
	WORD(4,"word","/word/",new String[]{"doc","docx","DOC","DOCX"}),
	EXCEL(5,"excel","/excel/",new String[]{"xls","xlsx","XLS","XLSX"}),
	PDF(6,"pdf","/pdf/",new String[]{"pdf","PDF"}),
	PPT(7,"ppt","/ppt/",new String[]{"ppt","pptx","PPT","PPTX"});

	private int code;
	private String desc;
	private String path;
	private String[] ext;

	FileType(int code, String desc,String path,String[] ext){
		this.code=code;
		this.desc=desc;
		this.path=path;
		this.ext=ext;
	}


	public int getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}

	/**
	 * 根据文件类型获取路径
	 * @param code
	 * @return
	 */
	public static String getPath(int code){
		for (FileType fileType : FileType.values()) {
			if(fileType.getCode()==code){
				return fileType.getPath();
			}
		}
		return FILE.getPath();
	}

	/**
	 * 根据扩展名获取路径
	 * @param ext
	 * @return
	 */
	public static String getPath(String ext){
		for (FileType fileType : FileType.values()) {
			String[] exts = fileType.getExt();
			for (String fileTypeExt : exts) {
				if(fileTypeExt.equalsIgnoreCase(ext)){
					return fileType.getPath();
				}
			}
		}
		return FILE.getPath();
	}

	public String getPath(){
		return path;
	}

	public String[] getExt() { return ext; }
}
