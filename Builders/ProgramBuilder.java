package Builders;
import Instructions.*;

public class ProgramBuilder extends BlockBuilder<ProgramBuilder>{

    private final String name;

    public ProgramBuilder(String name) {
        super();
        this.name = name;
    }

    public String getName() {return name;}

    public Program build() {return new Program(this);}

}
