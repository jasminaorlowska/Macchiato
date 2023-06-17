package Builders;
import Instructions.*;


public class BlockBuilder extends BlockProgramBuilder<Block> {

    public BlockBuilder() {
        super();
    }

    public Block build() {
        return new Block(this);
    }

}
