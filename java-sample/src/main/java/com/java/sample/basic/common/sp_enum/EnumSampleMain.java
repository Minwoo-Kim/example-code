package com.java.sample.basic.common.sp_enum;

import java.util.List;

import com.java.sample.basic.common.sp_enum.vo.EnumCodeDVO;

public class EnumSampleMain {

	public static void main(String[] args) {
		EnumSampleMain main = new EnumSampleMain();
		// main.sampleOne();
		// main.sampleTwo();
		main.sampleThree();
	}

	public void sampleOne() {
		String enumName = EnumSampleOne.APPLE.name();
		String color = EnumSampleOne.APPLE.getColor();
		String name = EnumSampleOne.APPLE.getName();

		System.out.println(enumName);
		System.out.println(color);
		System.out.println(name);

		EnumSampleOne[] et = EnumSampleOne.values();
		for (EnumSampleOne value : et) {
			System.out.println(value.name());
			System.out.println(value.getName());
			System.out.println(value.getColor());
		}
	}

	public void sampleTwo() {
		{
			String enumName = EnumSampleTwo.STAUTS_CODE.name();
			String code = EnumSampleTwo.STAUTS_CODE.getCode();
			String codeName = EnumSampleTwo.STAUTS_CODE.getCodeNm();
			List<EnumCodeDVO> codeList = EnumSampleTwo.STAUTS_CODE.getCodeList();

			System.out.println(enumName);
			System.out.println(code);
			System.out.println(codeName);

			codeList.forEach(vo -> System.out.println(vo.getCode() + " : " + vo.getCodeNm()));
		}
		{
			String enumName = EnumSampleTwo.YN_CODE.name();
			String code = EnumSampleTwo.YN_CODE.getCode();
			String codeName = EnumSampleTwo.YN_CODE.getCodeNm();
			List<EnumCodeDVO> codeList = EnumSampleTwo.YN_CODE.getCodeList();

			System.out.println(enumName);
			System.out.println(code);
			System.out.println(codeName);

			codeList.forEach(vo -> System.out.println(vo.getCode() + " : " + vo.getCodeNm()));
		}

		EnumSampleTwo[] et = EnumSampleTwo.values();
		for (EnumSampleTwo value : et) {
			System.out.println(value.name());
			System.out.println(value.getCode());
			System.out.println(value.getCodeNm());
		}
	}

	public void sampleThree() {
		String initPassKey = EnumSampleThree.USER_INIT_PASS.key;
		String initPassValue = EnumSampleThree.USER_INIT_PASS.defaultValue;
		System.out.println(initPassKey + " : " + initPassValue);

		String expireDateKey = EnumSampleThree.USER_EXPIRE_DATE.key;
		String expireDateValue = EnumSampleThree.USER_EXPIRE_DATE.defaultValue;
		System.out.println(expireDateKey + " : " + expireDateValue);

		String idLengthKey = EnumSampleThree.USER_ID_LENGTH.key;
		String idLengthValue = EnumSampleThree.USER_ID_LENGTH.defaultValue;
		System.out.println(idLengthKey + " : " + idLengthValue);

		String passLengthKey = EnumSampleThree.USER_PASSWORD_LENGTH.key;
		String passLengthValue = EnumSampleThree.USER_PASSWORD_LENGTH.defaultValue;
		System.out.println(passLengthKey + " : " + passLengthValue);

		String passLockCountKey = EnumSampleThree.USER_PASSWORD_LOCK_COUNT.key;
		String passLockCountValue = EnumSampleThree.USER_PASSWORD_LOCK_COUNT.defaultValue;
		System.out.println(passLockCountKey + " : " + passLockCountValue);
	}
}
