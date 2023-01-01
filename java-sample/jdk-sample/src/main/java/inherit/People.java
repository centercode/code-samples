package inherit;

public class People extends BaseGreet implements IGreet {

    /**
     * error without this method
     */
    @Override
    public void sayHello() {
        super.sayHello();
    }
}
