package com.qianliusi.qq.group;

import com.manyi.utils.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * Created by qianliusi on 2017/7/14.
 */
@Data
public class QQMember {
	private String card;
	private Integer flag;
	private Integer g;
	private Date join_time;
	private Date last_speak_time;
	private QQLevel lv;
	private String nick;
	private Integer qage;
	private Integer role;
	private String tags;
	private Long uin;
	public String getGender(){
		if(g == 0) {
			return "男";
		}
		if(g == 1) {
			return "女";
		}
		return "";
	}
	public String getJoin_timeStr(){
		return DateUtil.dateToString(getJoin_time());

	}
	public String getLast_speak_timeStr(){
		return DateUtil.dateToString(getLast_speak_time());
	}
}
