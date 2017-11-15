package com.cachet.common.properies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：配置文件加载
 */
@Component
public class PropertiesItems {

	//config.properties
	@Value("${config.imgPath}")
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
