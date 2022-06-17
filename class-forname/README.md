# Class.forName()에 대해서

## 1. 들어가며

``` java
    Command command = (Command)Class.forName(cmsClass).newInstance();
```

`Class.forName` 은 JDBC 접속 시 많이 봤다.

```java
    Class.forName("oracle.jdbc.driver.OracleDriver");
```
기존에는 관례처럼 사용했기에 확실히 알아보자.

## 2. Java Reflection
Class.forName()은 사실 자바 리플렉션 API(Java Reflection API)의 일부이다. 자바 리플렉션 API란 간단히 말해서
**구체적인 클래스의 타입을 알지 못해도 클래스의 정보(변수, 메소드, 타입..) 등에 접근하게 해주는 API.** (동적 바인딩)

Reflection은 샐행중인 자바프로그램 내부를 검사하고 내부의 속성을 수정할 수 있도록 해준다. 그런데 한가지 드는 의문점이 있다.
구체적인 클래스 타입을 알지 못하는데 어떻게 접근할 수 있을까?

그 이유는 자바의 클래스 파일들은 바이트코드로 컴파일되어 static 영역에 저장된다. 그렇게 때문에 클래스 이름만 알면 클래스의 정보를 찾을 수 있다.

자바 리플렉션은 동적 바인딩 이라고도 하는데 Runtime에 타입이 정해진다는 말이다.

## 3. Sample
obj라는 이름의 객체가 Car 클래스의 move 메서드를 사용할 수 있을까?
 - **불가능**

obj라는 이름의 객체는 컴파일 타입에 Object로 타입이 결정됐기 때문에 Object클래스의 인스턴스 변수와 메서드만 사용할 수 있다.
```java
public static void main(String[] args) {
    Object obj = new Car("mwlee", 0);
    obj.move();    // 컴파일 에러 발생 (cannot find symbol)
}
```

위 오류를 Reflection API를 이용해 가능토록 수정.
move 메서드가 실행되고 0으로 최기화 했던 Car 클래스 인스턴스 변수 position이 1로 출력.
Reflection API로 구체적인 클래스 Car 타입을 알지 못해도 move 메서드에 접근한 것이다.
```java
public static void main(String[] args) throws Exception {
    Object obj = new Car("mwlee", 0);
    Class carClass = Car.class;
    Method move = carClass.getMethod("move");

    // move 메서드 실행, invoke(메서드를 실행시킬 객체, 해당 메서드에 넘길 인자)
    move.invoke(obj, null);

    Method getPosition = carClass.getMethod("getPosition");
    int position = (int)getPosition.invoke(obj, null);
    System.out.println(position);
    // 출력 결과: 1
}
```

## 4. 단점
치명적인 단점 중 대표적으로 성능 오버헤드가 있다. 컴파일 타임이 아닌 런타임에 동적으로 타입을 분석하고 정보를 가져오므로 JVM을 최적화할 수 없기 떄문.
뿐만 아니라 직접 접근할수 없는 private 인스턴스 변수, 메서스데 접근 가능하므로 추상화가 깨진다.

## 5. 결론
Reflection은 애플리케이션 개발보다는 프레임워크나 라이브러리에서 많이 사용된다. 프레임워크나 라이브러리 에서는 사용자가 어떤 클래스를 만들지 예측할 수 없기 때문에 동적으로 해결해주기 위해 Reflection을 사용한다.
실제로 intellij의 자동완성, jackson 라이브러리, Hibernate 등등 많은 프레임워크나 라이브러리에서 Reflection을 사용하고 있다.
Spring Framework에서도 Reflection API를 사용하는데 대표적으로 Spring Container의 BeanFactory가 있다. Bean은 애플리케이션이 실행한 후 런타임에 객체가 호출될 때 동적으로 객체의 인스턴스를 생성하는데 이때 Spring Container의 BeanFactory에서 리플렉션을 사용한다.

Spring Data JPA에서 Entity에 기본 생성자가 필요한 이유도 동적으로 객체 생성 시 Reflection API를 활용하기 때문이다.
Reflection API로 가져올 수 없는 정보 중 하나가 생성자의 인자 정보이다. 그래서 기본 생성자가 반드시 있어야 객체를 생성할 수 있는 것이다.
기본 생성자로 객체를 생성만 하면 필드 값 등은 Reflection API로 넣어줄 수 있다.

## 6. 글을 마치며
사실 Java Reflection에는 `Class.forName()` 말고도 많은 메서드가 존재한다.
앞으로 Spring Framework등에 reflection에 관한 내용을 접하게 된다면 '프레임워크에서 구체적이지 않은 객체를 받아서 동적으로 해결해주것' 로 이해를 하면 될것 같다.



