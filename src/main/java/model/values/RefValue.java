package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{
    private int address;
    private IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return this.address;
    }

    public IType getLocationType() {
        return this.locationType;
    }

    @Override
    public boolean equals(IValue value) {
        return value.getType() instanceof RefType && ((RefValue) value).getAddress() == this.address && this.getType().equals(value.getType());
    }

    @Override
    public IType getType() {
        return new RefType(this.locationType);
    }

    @Override
    public String toString() {
        return "Ref(" + this.address + ", " + this.locationType.toString() + ")";
    }
}
