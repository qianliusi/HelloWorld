package com.qianliusi.qq.group;

import lombok.Data;

import java.util.List;

/**
 * Created by qianliusi on 2017/7/14.
 */
@Data
public class SearchGroupMemberResult {
	private Integer adm_max;
	private Integer adm_num;
	private Integer count;
	private Integer ec;
	private Integer max_count;
	private List<QQMember> mems;
}
