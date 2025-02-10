package view;

public class Interpreter {
    public static void main(String[] args) {
//        IStatement statement1 = new CompositeStatement(
//            new VarDeclStatement("v", new IntType()),
//            new CompositeStatement(
//                new AssignStatement("v", new ValueExpression(new IntValue(2))),
//                new PrintStatement(new VariableExpression("v"))
//            )
//        );
//        ProgramState programState1 = new ProgramState(statement1, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository1 = new Repository(programState1, "log1.txt");
//        Controller controller1 = new Controller(repository1);
//
//        IStatement statement2 = new CompositeStatement(
//            new VarDeclStatement("a", new IntType()),
//            new CompositeStatement(
//                new VarDeclStatement("b", new IntType()),
//                new CompositeStatement(
//                    new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), ArithmeticOperation.MULTIPLY), ArithmeticOperation.ADD)),
//                    new CompositeStatement(
//                        new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), ArithmeticOperation.ADD)),
//                        new PrintStatement(new VariableExpression("b"))
//                    )
//                )
//            )
//        );
//        ProgramState programState2 = new ProgramState(statement2, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository2 = new Repository(programState2, "log2.txt");
//        Controller controller2 = new Controller(repository2);
//
//        IStatement statement3 = new CompositeStatement(
//            new VarDeclStatement("a", new BoolType()),
//            new CompositeStatement(
//                new VarDeclStatement("v", new IntType()),
//                new CompositeStatement(
//                    new AssignStatement("a", new ValueExpression(new BoolValue(true))),
//                    new CompositeStatement(
//                        new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
//                        new PrintStatement(new VariableExpression("v"))
//                    )
//                )
//            )
//        );
//        ProgramState programState3 = new ProgramState(statement3, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository3 = new Repository(programState3, "log3.txt");
//        Controller controller3 = new Controller(repository3);
//
//        IStatement statement4 = new CompositeStatement(
//            new VarDeclStatement("varf", new StringType()),
//            new CompositeStatement(
//                new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                new CompositeStatement(
//                    new OpenFileStatement(new VariableExpression("varf")),
//                    new CompositeStatement(
//                        new VarDeclStatement("varc", new IntType()),
//                        new CompositeStatement(
//                            new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                            new CompositeStatement(
//                                new PrintStatement(new VariableExpression("varc")),
//                                new CompositeStatement(
//                                    new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                    new CompositeStatement(
//                                        new PrintStatement(new VariableExpression("varc")),
//                                        new CloseFileStatement(new VariableExpression("varf"))
//                                    )
//                                )
//                            )
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState4 = new ProgramState(statement4, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository4 = new Repository(programState4, "log4.txt");
//        Controller controller4 = new Controller(repository4);
//
//        IStatement statement5 = new CompositeStatement(
//            new VarDeclStatement("a", new IntType()),
//            new CompositeStatement(
//                new VarDeclStatement("b", new IntType()),
//                new CompositeStatement(
//                    new AssignStatement("a", new ValueExpression(new IntValue(1))),
//                    new CompositeStatement(
//                        new AssignStatement("b", new ValueExpression(new IntValue(2))),
//                        new CompositeStatement(
//                            new VarDeclStatement("c", new BoolType()),
//                            new CompositeStatement(
//                                new AssignStatement("c", new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), RelationalOperation.NOT_EQUAL)),
//                                new PrintStatement(new VariableExpression("c"))
//                            )
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState5 = new ProgramState(statement5, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository5 = new Repository(programState5, "log5.txt");
//        Controller controller5 = new Controller(repository5);
//
//        IStatement statement6 = new CompositeStatement(
//            new VarDeclStatement("x", new IntType()),
//            new CompositeStatement(
//                new AssignStatement("x", new ValueExpression(new IntValue(3))),
//                new WhileStatement(new RelationalExpression(new VariableExpression("x"), new ValueExpression(new IntValue(1)), RelationalOperation.GREATER_EQUAL), new CompositeStatement(new AssignStatement("x", new ArithmeticExpression(new VariableExpression("x"), new ValueExpression(new IntValue(1)), ArithmeticOperation.SUBTRACT)), new PrintStatement(new VariableExpression("x"))))
//            )
//        );
//        ProgramState programState6 = new ProgramState(statement6, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository6 = new Repository(programState6, "log6.txt");
//        Controller controller6 = new Controller(repository6);
//
//        IStatement statement7 = new CompositeStatement(
//            new VarDeclStatement("v", new RefType(new IntType())),
//            new CompositeStatement(
//                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                new CompositeStatement(
//                    new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                    new CompositeStatement(
//                        new HeapAllocationStatement("a", new VariableExpression("v")),
//                        new CompositeStatement(
//                            new PrintStatement(new VariableExpression("v")),
//                            new PrintStatement(new VariableExpression("a"))
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState7 = new ProgramState(statement7, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository7 = new Repository(programState7, "log7.txt");
//        Controller controller7 = new Controller(repository7);
//
//        IStatement statement8 = new CompositeStatement(
//            new VarDeclStatement("v", new RefType(new IntType())),
//            new CompositeStatement(
//                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                new CompositeStatement(
//                    new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                    new CompositeStatement(
//                        new HeapAllocationStatement("a", new VariableExpression("v")),
//                        new CompositeStatement(
//                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
//                                new PrintStatement(new ArithmeticExpression(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)), ArithmeticOperation.ADD))
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState8 = new ProgramState(statement8, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository8 = new Repository(programState8, "log8.txt");
//        Controller controller8 = new Controller(repository8);
//
//        IStatement statement9 = new CompositeStatement(
//            new VarDeclStatement("v", new RefType(new IntType())),
//            new CompositeStatement(
//                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                new CompositeStatement(
//                    new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
//                    new CompositeStatement(
//                        new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
//                        new PrintStatement(new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)), ArithmeticOperation.ADD))
//                    )
//                )
//            )
//        );
//        ProgramState programState9 = new ProgramState(statement9, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository9 = new Repository(programState9, "log9.txt");
//        Controller controller9 = new Controller(repository9);
//
//        IStatement statement10 = new CompositeStatement(
//            new VarDeclStatement("v", new RefType(new IntType())),
//            new CompositeStatement(
//                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                new CompositeStatement(
//                    new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                    new CompositeStatement(
//                        new HeapAllocationStatement("a", new VariableExpression("v")),
//                        new CompositeStatement(
//                            new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
//                            new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState10 = new ProgramState(statement10, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository10 = new Repository(programState10, "log10.txt");
//        Controller controller10 = new Controller(repository10);
//
//        IStatement statement11 = new CompositeStatement(
//            new VarDeclStatement("v", new RefType(new IntType())),
//            new CompositeStatement(
//                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                new CompositeStatement(
//                    new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
//                    new NopStatement()
//                )
//            )
//        );
//        ProgramState programState11 = new ProgramState(statement11, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository11 = new Repository(programState11, "log11.txt");
//        Controller controller11 = new Controller(repository11);
//
//        IStatement temp = new CompositeStatement(
//            new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
//            new CompositeStatement(
//                new AssignStatement("v", new ValueExpression(new IntValue(32))),
//                new CompositeStatement(
//                    new PrintStatement(new VariableExpression("v")),
//                    new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
//                )
//            )
//        );
//        IStatement statement12 = new CompositeStatement(
//            new VarDeclStatement("v", new IntType()),
//            new CompositeStatement(
//                new VarDeclStatement("a", new RefType(new IntType())),
//                new CompositeStatement(
//                    new AssignStatement("v", new ValueExpression(new IntValue(10))),
//                    new CompositeStatement(
//                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
//                        new CompositeStatement(
//                            new ForkStatement(temp),
//                            new CompositeStatement(
//                                new PrintStatement(new VariableExpression("v")),
//                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
//                            )
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState12 = new ProgramState(statement12, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository12 = new Repository(programState12, "log12.txt");
//        Controller controller12 = new Controller(repository12);
//
//        TextMenu textMenu = new TextMenu();
//        textMenu.addCommand(new ExitCommand("0", "exit"));
//        textMenu.addCommand(new RunCommand("1", statement1.toString(), controller1));
//        textMenu.addCommand(new RunCommand("2", statement2.toString(), controller2));
//        textMenu.addCommand(new RunCommand("3", statement3.toString(), controller3));
//        textMenu.addCommand(new RunCommand("4", statement4.toString(), controller4));
//        textMenu.addCommand(new RunCommand("5", statement5.toString(), controller5));
//        textMenu.addCommand(new RunCommand("6", statement6.toString(), controller6));
//        textMenu.addCommand(new RunCommand("7", statement7.toString(), controller7));
//        textMenu.addCommand(new RunCommand("8", statement8.toString(), controller8));
//        textMenu.addCommand(new RunCommand("9", statement9.toString(), controller9));
//        textMenu.addCommand(new RunCommand("10", statement10.toString(), controller10));
//        textMenu.addCommand(new RunCommand("11", statement11.toString(), controller11));
//        textMenu.addCommand(new RunCommand("12", statement12.toString(), controller12));
//        textMenu.run();
    }
}