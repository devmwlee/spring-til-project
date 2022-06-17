# Thread

## 프로세스 & 쓰레드
- 프로세스(Process)
  - 프로세스는 실행 중인 프로그램으로 디스크로부터 메모리에 적재되어 CPU의 할당을 받을 수 있는 것을 말한다. 간단하게 말해서 컴퓨터에서 연속적으로 실행되고 있는 컴퓨터 프로그램을 의미.
- 쓰레드(Thread)
  - 쓰레드는 프로세스의 실행 단위. 한 프로세스 내에서 동작되는 여러 실행의 흐름으로 프로세스 내의 주소 공간이나 자원을 공유할 수 있다.
  - 쓰레드는 ID, PC, 레지스터, 스택으로 구성된다. 같은 프로세스에 속한 쓰레드는 코드, 데이터, 파일과 같은 운영체제 자원들을 공유한다.
  - 스택(stack) : 각각 쓰레드에 독립적인 메모리 저장 공간(인자, 주소값 등..이 저장된다)

## Java에서 Thread를 생성하는 2가지 방법 (코드 참조)
- `Runnable` interface를 구현(implements) 하는 방법
- `Thread`를 상속(extends) 하는 방법

### Runnable Interface
가장 쉬운 방법은 `Runnable` interface를 구현하는 방법이다. Runnable interface는 run 메소드만 구현해주면 된다.

### Extending Thread
`Thread`를 상속받는 방법이다. `Thread`를 상속받은 뒤 마찬가지로 run메소드를 구현해주면 된다.
`Runnable` interface를 구현한 것과 다른점은 `Thread`를 상속 받았기 때문에 바로 `start()`메소스 실행 가능

## Sync & Async
Synchronized method/block (코드 참조)
동기화를 구현하는데는 2가지 방식이 있다. method구현과 block형식의 구현
block방식에서는 synchronized 메서드 안에만 동기화 방식으로 동작하기 떄문에 연속으로 출력 된 것 이다. (methodA 시작) 가 먼저 출력된 이유



