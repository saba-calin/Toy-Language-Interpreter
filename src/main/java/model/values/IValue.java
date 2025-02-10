package model.values;

import model.types.IType;

public interface IValue {
    boolean equals(IValue value);
    IType getType();
}
