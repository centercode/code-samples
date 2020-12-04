package object;

public class CloneSample implements Cloneable {

    private int id;

    private String name;

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneSample sample = new CloneSample();
        sample.id = 123;
        sample.name = "david";
        Object sampleClone = sample.clone();
        System.out.println(sample);
        System.out.println(sampleClone);
        System.out.println("sampleClone == sample: " + (sampleClone == sample));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "CloneSample{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
