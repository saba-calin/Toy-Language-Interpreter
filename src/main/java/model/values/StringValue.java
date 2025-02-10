package model.values;

import model.types.IType;
import model.types.StringType;

public class StringValue implements IValue {
    private String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getValue() {
        return this.val;
    }

    @Override
    public boolean equals(IValue value) {
        return value.getType() instanceof StringType && ((StringValue) value).getValue() == this.val;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return this.val;
    }
}
