package model.types;

import model.values.IValue;
import model.values.RefValue;

public class RefType implements IType {
    private IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(IType type) {
        return type instanceof RefType && this.inner.equals(((RefType) type).getInner());
    }

    @Override
    public IValue getDefaultValue() {
        return new RefValue(0, this.inner);
    }

    @Override
    public String toString() {
        return "Ref(" + this.inner.toString() + ")";
    }
}
