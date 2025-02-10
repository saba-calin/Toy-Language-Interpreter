package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType {
    @Override
    public boolean equals(IType type) {
        return type instanceof StringType;
    }

    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }
}
