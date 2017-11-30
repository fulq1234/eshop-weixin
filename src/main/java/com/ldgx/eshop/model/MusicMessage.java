package com.ldgx.eshop.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicMessage extends BaseMessage{
	
	@XmlElement(name="Music")
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
	
	
}
