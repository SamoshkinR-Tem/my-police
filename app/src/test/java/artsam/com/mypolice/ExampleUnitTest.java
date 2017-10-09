package artsam.com.mypolice;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private class Employee {
        private Integer age = 29;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @Test
    public void passingVariables() throws Exception {
        int i = 5;
        changeInt(i);
        System.out.println(i);

        Employee artem = new Employee();
        changeEmployee(artem);
        System.out.println(artem.getAge());
    }

    private void changeEmployee(Employee employee) {
        employee.age=30;
        System.out.println(employee.getAge());
    }

    private void changeInt(int i) {
        i = 6;
        System.out.println(i);
    }

}