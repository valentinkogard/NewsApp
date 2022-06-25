package at.ac.fhcampuswien.articleStructure;

public class Source {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    private Source(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder{
        private String id;
        private String name;

        public Builder(){}

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Source build(){
            return new Source(this);
        }
    }
}
