public class HelloWorldAnonymousClasses {
    
    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }

    public void sayHello() {

        //local class
        class EnglishGretting implements HelloWorld {
            String name = "world";
            public void greet() {
                greetSomeone("world");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        //instantiate local class
        HelloWorld englishGreeting = new EnglishGretting();

        //anonymous class - declaration & instantiation
        //it's an expression, note ';' at the end
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            public void greet() {
                greetSomeone("tout le monde");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };

        //anonymous class - declaration & instantiation
        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola " + name);
            }
        };

        //calling inner classes methods
        englishGreeting.greet();
        frenchGreeting.greetSomeone("Freddy");
        spanishGreeting.greet();
    }

    public static void main(String[] args) throws Exception {
        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }
}
