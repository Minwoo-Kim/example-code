package com.java.sample.basic.common.sp_enum;

import java.util.ArrayList;
import java.util.List;

import com.java.sample.basic.common.sp_enum.vo.EnumCodeDVO;

public enum EnumSampleTwo {
	STAUTS_CODE("SC", "Status Code") {
		public List<EnumCodeDVO> getCodeList() {
			EnumCodeDVO normalCodeDVO = new EnumCodeDVO();
			normalCodeDVO.setCode("NML");
			normalCodeDVO.setCodeNm("Normal");

			EnumCodeDVO lockCodeDVO = new EnumCodeDVO();
			lockCodeDVO.setCode("LCK");
			lockCodeDVO.setCodeNm("Lock");

			List<EnumCodeDVO> codeDVOList = new ArrayList<EnumCodeDVO>();
			codeDVOList.add(normalCodeDVO);
			codeDVOList.add(lockCodeDVO);

			return codeDVOList;
		}
	},
	YN_CODE("YN", "Y/N Code") {
		public List<EnumCodeDVO> getCodeList() {
			EnumCodeDVO yesCodeDVO = new EnumCodeDVO();
			yesCodeDVO.setCode("Y");
			yesCodeDVO.setCodeNm("Yes");

			EnumCodeDVO noCodeDVO = new EnumCodeDVO();
			noCodeDVO.setCode("N");
			noCodeDVO.setCodeNm("No");

			List<EnumCodeDVO> codeDVOList = new ArrayList<EnumCodeDVO>();
			codeDVOList.add(yesCodeDVO);
			codeDVOList.add(noCodeDVO);

			return codeDVOList;
		}
	};

	private String code;
	private String codeNm;

	EnumSampleTwo(String code, String codeNm) {
		this.code = code;
		this.codeNm = codeNm;
	}

	public String getCode() {
		return this.code;
	}

	public String getCodeNm() {
		return this.codeNm;
	}

	public List<EnumCodeDVO> getCodeList() {
		return null;
	}
}
