package uni_manager;

abstract class Person {
    private String name, family, id;

    public Person(String name, String family, String id) {
        this.name = name;
        this.family = family;
        this.id = id;
    }

    public abstract int calcMoneyChange(String sem);

    public String getName() { return this.name; }

    public String getFamily() { return this.family; }

    public String getId() { return this.id; }
}