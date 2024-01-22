public class OuterClass {

    String outerField = "Outer field";
    static String staticOuterField = "Static outer field";
    public int x = 0;

    //direct access to that object's methods and fields
    class InnerClass {
        void accessMembers() {
            System.out.println(outerField);
            System.out.println(staticOuterField);
        }
    }

    //access to that object's methods and fields like for a top-level class
    //static nested class StaticNestedClass can't directly access outerField
    static class StaticNestedClass {
        void accessMembers(OuterClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            //     field outerField
            // System.out.println(outerField);
            System.out.println(outer.outerField);
            System.out.println(staticOuterField);
        }
    }

    class SecondInnerClass {
        
        public int x = 1;

        void methodInSecondClass(int x) {
            System.out.println("x= " + x);
            System.out.println("this.x= " + this.x);
            System.out.println("OuterClass.this.x= " + OuterClass.this.x);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Inner class:");
        System.out.println("------------");
        OuterClass outerObject = new OuterClass();
        //instantiate like this:
        OuterClass.InnerClass innerObject = outerObject.new InnerClass();
        innerObject.accessMembers();

        System.out.println("\nStatic nested class:");
        System.out.println("----------------------");
        //instantiate like lop-level class
        StaticNestedClass staticNestedObject = new StaticNestedClass();
        staticNestedObject.accessMembers(outerObject);

        System.out.println("\nTop-level class:");
        System.out.println("------------------");
        TopLevelClass topLevelObject = new TopLevelClass();
        topLevelObject.accessMembers(outerObject);

        System.out.println("\nSHADOWING TEST:");
        OuterClass.SecondInnerClass second = outerObject.new SecondInnerClass();
        second.methodInSecondClass(23);
    }
}
