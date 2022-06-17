package dev.mwlee.classforname;

import oracle.jdbc.driver.OracleDriver;

import java.lang.reflect.Method;

public class ClassForName {
	public static void main(String[] args) {
		try {

			// 다형성 덕분에 아래와 같이 객체 생성 가능
			Object obj = new Car("mwlee", 0);
			// ojb.move(); // 컴파일 에러 발생 (cannot find symbol)

			Class carClass = Car.class;
			Method move = carClass.getMethod("move");
			move.invoke(obj, null);

			Method getPosition = carClass.getMethod("getPosition");
			int position = (int) getPosition.invoke(obj, null);
			System.out.println("위치는 : " + position);

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
