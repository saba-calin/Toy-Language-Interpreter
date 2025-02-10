package com.example.exam;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.adts.dictionary.MyDictionary;
import model.adts.heap.Heap;
import model.adts.latch.LatchTable;
import model.adts.list.MyList;
import model.adts.stack.MyStack;
import model.expressions.*;
import model.statements.*;
import model.statements.heapstatements.HeapAllocationStatement;
import model.statements.heapstatements.HeapWritingStatement;
import model.states.ProgramState;
import model.types.IntType;
import model.types.RefType;
import model.values.IValue;
import model.values.IntValue;
import repository.IRepository;
import repository.Repository;

import java.util.*;

public class GUI extends Application {
    private Stage mainStage;
    private Controller controller;
    private ProgramState programState;

    @Override
    public void start(Stage stage) {
//        IStatement statement1 = new CompositeStatement(
//                new VarDeclStatement("v", new IntType()),
//                new CompositeStatement(
//                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))
//                )
//        );
//        ProgramState programState1 = new ProgramState(statement1, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository1 = new Repository(programState1, "log1.txt");
//        Controller controller1 = new Controller(repository1);
//
//        IStatement statement2 = new CompositeStatement(
//                new VarDeclStatement("a", new IntType()),
//                new CompositeStatement(
//                        new VarDeclStatement("b", new IntType()),
//                        new CompositeStatement(
//                                new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), ArithmeticOperation.MULTIPLY), ArithmeticOperation.ADD)),
//                                new CompositeStatement(
//                                        new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), ArithmeticOperation.ADD)),
//                                        new PrintStatement(new VariableExpression("b"))
//                                )
//                        )
//                )
//        );
//        ProgramState programState2 = new ProgramState(statement2, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository2 = new Repository(programState2, "log2.txt");
//        Controller controller2 = new Controller(repository2);
//
//        IStatement statement3 = new CompositeStatement(
//                new VarDeclStatement("a", new BoolType()),
//                new CompositeStatement(
//                        new VarDeclStatement("v", new IntType()),
//                        new CompositeStatement(
//                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompositeStatement(
//                                        new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
//                                        new PrintStatement(new VariableExpression("v"))
//                                )
//                        )
//                )
//        );
//        ProgramState programState3 = new ProgramState(statement3, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository3 = new Repository(programState3, "log3.txt");
//        Controller controller3 = new Controller(repository3);
//
//        IStatement statement4 = new CompositeStatement(
//                new VarDeclStatement("varf", new StringType()),
//                new CompositeStatement(
//                        new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                        new CompositeStatement(
//                                new OpenFileStatement(new VariableExpression("varf")),
//                                new CompositeStatement(
//                                        new VarDeclStatement("varc", new IntType()),
//                                        new CompositeStatement(
//                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                                new CompositeStatement(
//                                                        new PrintStatement(new VariableExpression("varc")),
//                                                        new CompositeStatement(
//                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                                                new CompositeStatement(
//                                                                        new PrintStatement(new VariableExpression("varc")),
//                                                                        new CloseFileStatement(new VariableExpression("varf"))
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//        ProgramState programState4 = new ProgramState(statement4, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository4 = new Repository(programState4, "log4.txt");
//        Controller controller4 = new Controller(repository4);
//
//        IStatement statement5 = new CompositeStatement(
//                new VarDeclStatement("a", new IntType()),
//                new CompositeStatement(
//                        new VarDeclStatement("b", new IntType()),
//                        new CompositeStatement(
//                                new AssignStatement("a", new ValueExpression(new IntValue(1))),
//                                new CompositeStatement(
//                                        new AssignStatement("b", new ValueExpression(new IntValue(2))),
//                                        new CompositeStatement(
//                                                new VarDeclStatement("c", new BoolType()),
//                                                new CompositeStatement(
//                                                        new AssignStatement("c", new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), RelationalOperation.NOT_EQUAL)),
//                                                        new PrintStatement(new VariableExpression("c"))
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//        ProgramState programState5 = new ProgramState(statement5, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository5 = new Repository(programState5, "log5.txt");
//        Controller controller5 = new Controller(repository5);
//
//        IStatement statement6 = new CompositeStatement(
//                new VarDeclStatement("x", new IntType()),
//                new CompositeStatement(
//                        new AssignStatement("x", new ValueExpression(new IntValue(3))),
//                        new WhileStatement(new RelationalExpression(new VariableExpression("x"), new ValueExpression(new IntValue(1)), RelationalOperation.GREATER_EQUAL), new CompositeStatement(new AssignStatement("x", new ArithmeticExpression(new VariableExpression("x"), new ValueExpression(new IntValue(1)), ArithmeticOperation.SUBTRACT)), new PrintStatement(new VariableExpression("x"))))
//                )
//        );
//        ProgramState programState6 = new ProgramState(statement6, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository6 = new Repository(programState6, "log6.txt");
//        Controller controller6 = new Controller(repository6);
//
//        IStatement statement7 = new CompositeStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompositeStatement(
//                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompositeStatement(
//                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompositeStatement(
//                                        new HeapAllocationStatement("a", new VariableExpression("v")),
//                                        new CompositeStatement(
//                                                new PrintStatement(new VariableExpression("v")),
//                                                new PrintStatement(new VariableExpression("a"))
//                                        )
//                                )
//                        )
//                )
//        );
//        ProgramState programState7 = new ProgramState(statement7, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository7 = new Repository(programState7, "log7.txt");
//        Controller controller7 = new Controller(repository7);
//
//        IStatement statement8 = new CompositeStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompositeStatement(
//                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompositeStatement(
//                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompositeStatement(
//                                        new HeapAllocationStatement("a", new VariableExpression("v")),
//                                        new CompositeStatement(
//                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
//                                                new PrintStatement(new ArithmeticExpression(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)), ArithmeticOperation.ADD))
//                                        )
//                                )
//                        )
//                )
//        );
//        ProgramState programState8 = new ProgramState(statement8, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository8 = new Repository(programState8, "log8.txt");
//        Controller controller8 = new Controller(repository8);
//
//        IStatement statement9 = new CompositeStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompositeStatement(
//                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompositeStatement(
//                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
//                                new CompositeStatement(
//                                        new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
//                                        new PrintStatement(new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)), ArithmeticOperation.ADD))
//                                )
//                        )
//                )
//        );
//        ProgramState programState9 = new ProgramState(statement9, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository9 = new Repository(programState9, "log9.txt");
//        Controller controller9 = new Controller(repository9);
//
//        IStatement statement10 = new CompositeStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompositeStatement(
//                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompositeStatement(
//                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompositeStatement(
//                                        new HeapAllocationStatement("a", new VariableExpression("v")),
//                                        new CompositeStatement(
//                                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
//                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))
//                                        )
//                                )
//                        )
//                )
//        );
//        ProgramState programState10 = new ProgramState(statement10, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository10 = new Repository(programState10, "log10.txt");
//        Controller controller10 = new Controller(repository10);
//
//        IStatement statement11 = new CompositeStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompositeStatement(
//                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompositeStatement(
//                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
//                                new NopStatement()
//                        )
//                )
//        );
//        ProgramState programState11 = new ProgramState(statement11, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository11 = new Repository(programState11, "log11.txt");
//        Controller controller11 = new Controller(repository11);
//
//        IStatement temp = new CompositeStatement(
//                new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
//                new CompositeStatement(
//                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
//                        new CompositeStatement(
//                                new PrintStatement(new VariableExpression("v")),
//                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
//                        )
//                )
//        );
//        IStatement statement12 = new CompositeStatement(
//                new VarDeclStatement("v", new IntType()),
//                new CompositeStatement(
//                        new VarDeclStatement("a", new RefType(new IntType())),
//                        new CompositeStatement(
//                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
//                                new CompositeStatement(
//                                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
//                                        new CompositeStatement(
//                                                new ForkStatement(temp),
//                                                new CompositeStatement(
//                                                        new PrintStatement(new VariableExpression("v")),
//                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//        ProgramState programState12 = new ProgramState(statement12, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap());
//        IRepository repository12 = new Repository(programState12, "log12.txt");
//        Controller controller12 = new Controller(repository12);


//        IStatement fork = new CompositeStatement(
//            new AssignStatement("a", new ValueExpression(new IntValue(15))),
//            new NopStatement()
//        );
//        IStatement statement13 = new CompositeStatement(
//            new VarDeclStatement("a", new IntType()),
//            new CompositeStatement(
//                new AssignStatement("a", new ValueExpression(new IntValue(10))),
//                new CompositeStatement(
//                    new ForkStatement(fork),
//                    new CompositeStatement(
//                        new NopStatement(),
//                        new CompositeStatement(
//                            new NopStatement(),
//                            new NopStatement()
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState13 = new ProgramState(statement13, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap(), new LatchTable());
//        IRepository repository13 = new Repository(programState13, "log13.txt");
//        Controller controller13 = new Controller(repository13);

        IStatement statement14 = new CompositeStatement(
            new VarDeclStatement("a", new RefType(new IntType())),
            new CompositeStatement(
                new VarDeclStatement("b", new RefType(new IntType())),
                new CompositeStatement(
                    new VarDeclStatement("v", new IntType()),
                    new CompositeStatement(
                        new HeapAllocationStatement("a", new ValueExpression(new IntValue(0))),
                        new CompositeStatement(
                            new HeapAllocationStatement("b", new ValueExpression(new IntValue(0))),
                            new CompositeStatement(
                                new HeapWritingStatement("a", new ValueExpression(new IntValue(1))),
                                new CompositeStatement(
                                    new HeapWritingStatement("b", new ValueExpression(new IntValue(2))),
                                    new CompositeStatement(
                                        new CondAssignmentStatement("v", new RelationalExpression(new HeapReadingExpression(new VariableExpression("a")), new HeapReadingExpression(new VariableExpression("b")), RelationalOperation.LESS), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))),
                                        new CompositeStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new CompositeStatement(
                                                new CondAssignmentStatement("v", new RelationalExpression(new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("b")), new ValueExpression(new IntValue(2)), ArithmeticOperation.SUBTRACT), new HeapReadingExpression(new VariableExpression("a")), RelationalOperation.GREATER), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))),
                                                new PrintStatement(new VariableExpression("v"))
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );
        ProgramState programState14 = new ProgramState(statement14, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap(), new LatchTable());
        IRepository repository14 = new Repository(programState14, "log14.txt");
        Controller controller14 = new Controller(repository14);

//        IStatement fork1 = new CompositeStatement(
//            new HeapWritingStatement("v1", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), ArithmeticOperation.MULTIPLY)),
//            new CompositeStatement(
//                new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))),
//                new CountdownStatement("cnt")
//            )
//        );
//        IStatement fork2 = new CompositeStatement(
//            new HeapWritingStatement("v2", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)), ArithmeticOperation.MULTIPLY)),
//            new CompositeStatement(
//                new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))),
//                new CountdownStatement("cnt")
//            )
//        );
//        IStatement fork3 = new CompositeStatement(
//            new HeapWritingStatement("v3", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)), ArithmeticOperation.MULTIPLY)),
//            new CompositeStatement(
//                new PrintStatement(new HeapReadingExpression(new VariableExpression("v3"))),
//                new CountdownStatement("cnt")
//            )
//        );
//        IStatement statement15 = new CompositeStatement(
//            new VarDeclStatement("v1", new RefType(new IntType())),
//            new CompositeStatement(
//                new VarDeclStatement("v2", new RefType(new IntType())),
//                new CompositeStatement(
//                    new VarDeclStatement("v3", new RefType(new IntType())),
//                    new CompositeStatement(
//                        new VarDeclStatement("cnt", new IntType()),
//                        new CompositeStatement(
//                            new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
//                            new CompositeStatement(
//                                new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3))),
//                                new CompositeStatement(
//                                    new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4))),
//                                    new CompositeStatement(
//                                        new CreateLatchStatement("cnt", new HeapReadingExpression(new VariableExpression("v2"))),
//                                        new CompositeStatement(
//                                            new ForkStatement(fork1),
//                                            new CompositeStatement(
//                                                new ForkStatement(fork2),
//                                                new CompositeStatement(
//                                                    new ForkStatement(fork3),
//                                                    new CompositeStatement(
//                                                        new AwaitStatement("cnt"),
//                                                        new CompositeStatement(
//                                                            new PrintStatement(new ValueExpression(new IntValue(100))),
//                                                            new CompositeStatement(
//                                                                new CountdownStatement("cnt"),
//                                                                new PrintStatement(new ValueExpression(new IntValue(100)))
//                                                            )
//                                                        )
//                                                    )
//                                                )
//                                            )
//                                        )
//                                    )
//                                )
//                            )
//                        )
//                    )
//                )
//            )
//        );
//        ProgramState programState15 = new ProgramState(statement15, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap(), new LatchTable());
//        IRepository repository15 = new Repository(programState15, "log15.txt");
//        Controller controller15 = new Controller(repository15);


        IStatement fork3 = new CompositeStatement(
            new HeapWritingStatement("v3", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)), ArithmeticOperation.MULTIPLY)),
            new CompositeStatement(
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v3"))),
                new CountdownStatement("cnt")
            )
        );
        IStatement fork2 = new CompositeStatement(
            new HeapWritingStatement("v2", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)), ArithmeticOperation.MULTIPLY)),
            new CompositeStatement(
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))),
                new CompositeStatement(
                    new CountdownStatement("cnt"),
                    new ForkStatement(fork3)
                )
            )
        );
        IStatement fork1 = new CompositeStatement(
            new HeapWritingStatement("v1", new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), ArithmeticOperation.MULTIPLY)),
            new CompositeStatement(
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))),
                new CompositeStatement(
                    new CountdownStatement("cnt"),
                    new ForkStatement(fork2)
                )
            )
        );

        IStatement statement15 = new CompositeStatement(
            new VarDeclStatement("v1", new RefType(new IntType())),
            new CompositeStatement(
                new VarDeclStatement("v2", new RefType(new IntType())),
                new CompositeStatement(
                    new VarDeclStatement("v3", new RefType(new IntType())),
                    new CompositeStatement(
                        new VarDeclStatement("cnt", new IntType()),
                        new CompositeStatement(
                            new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
                            new CompositeStatement(
                                new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3))),
                                new CompositeStatement(
                                    new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4))),
                                    new CompositeStatement(
                                        new CreateLatchStatement("cnt", new HeapReadingExpression(new VariableExpression("v2"))),
                                        new CompositeStatement(
                                            new ForkStatement(fork1),
                                            new CompositeStatement(
                                                new AwaitStatement("cnt"),
                                                new CompositeStatement(
                                                    new PrintStatement(new ValueExpression(new IntValue(100))),
                                                    new CompositeStatement(
                                                        new CountdownStatement("cnt"),
                                                        new PrintStatement(new ValueExpression(new IntValue(100)))
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );
        ProgramState programState15 = new ProgramState(statement15, new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new Heap(), new LatchTable());
        IRepository repository15 = new Repository(programState15, "log15.txt");
        Controller controller15 = new Controller(repository15);


        List<Pair<IStatement, Controller>> list = new ArrayList<>();
//        list.add(new Pair<>(statement1, controller1));
//        list.add(new Pair<>(statement2, controller2));
//        list.add(new Pair<>(statement3, controller3));
//        list.add(new Pair<>(statement4, controller4));
//        list.add(new Pair<>(statement5, controller5));
//        list.add(new Pair<>(statement6, controller6));
//        list.add(new Pair<>(statement7, controller7));
//        list.add(new Pair<>(statement8, controller8));
//        list.add(new Pair<>(statement9, controller9));
//        list.add(new Pair<>(statement10, controller10));
//        list.add(new Pair<>(statement11, controller11));
//        list.add(new Pair<>(statement12, controller12));
//        list.add(new Pair<>(statement13, controller13));
        list.add(new Pair<>(statement14, controller14));
        list.add(new Pair<>(statement15, controller15));

        this.mainStage = new Stage();
        displayProgramsWindow(list);
        displayMainWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    private void displayMainWindow() {
        VBox vbox = new VBox();


        // Number of program states
        TextField programStatesNumber = new TextField();
        programStatesNumber.setEditable(false);
        try {
            programStatesNumber.setText("Number of program states: " + controller.getProgramsList().size());
        }
        catch (Exception e) {
            programStatesNumber.setText("Number of program states: 0");
        }


        // Heap Table
        TextField heapTextField = new TextField("Heap Table: ");
        heapTextField.setEditable(false);

        TableView<StringIValue> heapTableView = new TableView<>();
        TableColumn<StringIValue, String> heapKeyColumn = new TableColumn<>("Address");
        heapKeyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        TableColumn<StringIValue, IValue> heapValueColumn = new TableColumn<>("Value");
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        heapTableView.getColumns().add(heapKeyColumn);
        heapTableView.getColumns().add(heapValueColumn);
        try {
            ObservableList<StringIValue> list = FXCollections.observableArrayList();

            for (Map.Entry<Integer, IValue> entry : this.programState.getHeap().getContent().entrySet()) {
                StringIValue stringIValue = new StringIValue(entry.getKey().toString(), entry.getValue());
                // System.out.println(stringIValue);
                list.add(stringIValue);
            }

            heapTableView.setItems(list);
        }
        catch (Exception e) {
            if (this.programState != null) {
                System.out.println(e);
            }
        }


        // Output List
        TextField outTextField = new TextField("Output List: ");
        outTextField.setEditable(false);

        ListView<String> outList = new ListView<>();
        outList.setEditable(false);
        try {
            outList.getItems().addAll(this.programState.getOutputList().getAll());
        }
        catch (Exception e){
            if (this.programState != null) {
                System.out.println(e);
            }
        }


        // File Table
        TextField fileTextField = new TextField("File Table: ");
        fileTextField.setEditable(false);

        ListView<String> fileList = new ListView<>();
        fileList.setEditable(false);
        try {
            fileList.getItems().addAll(this.programState.getFileList().getAll());
        }
        catch (Exception e) {
            if (this.programState != null) {
                System.out.println(e);
            }
        }


        // Program States
        String currentId = "null";
        try {
            currentId = Integer.toString(this.programState.getId());
        }
        catch (Exception e) {
            if (this.programState != null) {
                System.out.println(e);
            }
        }
        TextField statesTextField = new TextField("Program States(" + currentId + "): ");
        statesTextField.setEditable(false);

        ListView<String> statesList = new ListView<>();
        statesList.setEditable(false);
        try {
            List<Integer> ids = this.controller.getIds();
            for (int id : ids) {
                String temp = "Program State with id: " + id;
                statesList.getItems().add(temp);
            }
        }
        catch (Exception e) {
            if (this.controller != null) {
                System.out.println(e);
            }
        }

        statesList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String temp = statesList.getSelectionModel().getSelectedItem();
                int id = Integer.parseInt(temp.split(" ")[4]);
//                System.out.println(id);
                this.programState = this.controller.getProgramStateById(id);
                displayMainWindow();
            }
        });


        // Symbol Table
        TextField symbolTextField = new TextField("Symbol Table: ");
        symbolTextField.setEditable(false);

        TableView<StringIValue> symbolTableView = new TableView<>();
        symbolTableView.setEditable(false);
        TableColumn<StringIValue, String> keyCol = new TableColumn<>("Variable Name");
        keyCol.setCellValueFactory(new PropertyValueFactory<>("key"));
        TableColumn<StringIValue, String> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        symbolTableView.getColumns().add(keyCol);
        symbolTableView.getColumns().add(valueCol);
        try {
            ObservableList<StringIValue> list = FXCollections.observableArrayList();

            for (Map.Entry<String, IValue> entry : this.programState.getSymbolTable().getContent().entrySet()) {
                StringIValue stringIValue = new StringIValue(entry.getKey(), entry.getValue());
                list.add(stringIValue);
            }

            symbolTableView.setItems(list);
        }
        catch (Exception e) {
            if (this.programState != null) {
                System.out.println(e);
            }
        }


        // Latch Table
        TextField latchTextField = new TextField("Latch Table: ");
        latchTextField.setEditable(false);

        TableView<LatchTableView> latchTableView = new TableView<>();
        latchTableView.setEditable(false);
        TableColumn<LatchTableView, Integer> addressCol = new TableColumn<>("Location");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<LatchTableView, Integer> valueCol1 = new TableColumn<>("Value");
        valueCol1.setCellValueFactory(new PropertyValueFactory<>("value"));
        latchTableView.getColumns().add(addressCol);
        latchTableView.getColumns().add(valueCol1);
        try {
            ObservableList<LatchTableView> list = FXCollections.observableArrayList();

            for (Map.Entry<Integer, Integer> entry : this.programState.getLatchTable().getContent().entrySet()) {
                LatchTableView latchTableView1 = new LatchTableView(entry.getKey(), entry.getValue());
                list.add(latchTableView1);
            }

            latchTableView.setItems(list);
        }
        catch (Exception e) {
            if (this.programState != null) {
                System.out.println(e);
            }
        }


        // Execution Stack
        TextField stackTextField = new TextField("Execution Stack:");
        stackTextField.setEditable(false);
        ListView<String> stackList = new ListView<>();
        stackList.setEditable(false);
        try {
            Stack<IStatement> stack = this.programState.getExecStack().getAll();
            for (IStatement statement : stack) {
                stackList.getItems().add(statement.toString());
            }
        }
        catch (Exception e) {
            if (this.programState != null) {
                System.out.println(e);
            }
        }


        // One Step Button
        Button oneStepButton = new Button("One Step");
        oneStepButton.setOnAction(_ -> {
            try {
                this.controller.UpdatedOneStep();
//                this.programState = this.controller.getProgramsList().getFirst();

                if (this.controller.containsProgramStateWithId(this.programState.getId()) == false && this.controller.getProgramsList().size() != 0) {
                    this.programState = this.controller.getProgramsList().getFirst();
                }
            }
            catch (Exception e) {
//                e.printStackTrace();
                if (this.controller != null) {
                    System.out.println(e);
                }
            }
            displayMainWindow();
        });

        vbox.getChildren().add(programStatesNumber);
        vbox.getChildren().add(heapTextField);
        vbox.getChildren().add(heapTableView);
        vbox.getChildren().add(outTextField);
        vbox.getChildren().add(outList);
        vbox.getChildren().add(fileTextField);
        vbox.getChildren().add(fileList);
        vbox.getChildren().add(statesTextField);
        vbox.getChildren().add(statesList);
        vbox.getChildren().add(symbolTextField);
        vbox.getChildren().add(symbolTableView);
        vbox.getChildren().add(latchTextField);
        vbox.getChildren().add(latchTableView);
        vbox.getChildren().add(stackTextField);
        vbox.getChildren().add(stackList);
        vbox.getChildren().add(oneStepButton);

        Scene scene = new Scene(vbox, 400, 800);
        this.mainStage.setScene(scene);
        this.mainStage.setTitle("Main Window");
        this.mainStage.show();
    }

    private void displayProgramsWindow(List<Pair<IStatement, Controller>> list) {
        ListView<String> programsList = new ListView<>();
        for (int i = 0; i < list.size(); i++) {
            IStatement statement = list.get(i).getKey();
            programsList.getItems().add(statement.toString());
        }
        programsList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String string = programsList.getSelectionModel().getSelectedItem();

                for (int i = 0; i < list.size(); i++) {
                    if (Objects.equals(list.get(i).getKey().toString(), string)) {
                        System.out.println(string);

                        this.controller = list.get(i).getValue();
                        this.programState = this.controller.getProgramsList().getFirst();

                        displayMainWindow();

//                        try {
//                            this.controller.allSteps();
//                        }
//                        catch (Exception exception) {
//                            System.out.println(exception);
//                        }

                        break;
                    }
                }
            }
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(programsList);

        Scene scene = new Scene(stackPane, 800, 400);

        Stage stage = new Stage();
        stage.setTitle("Programs Window");
        stage.setScene(scene);
        stage.show();
    }

    public static class StringIValue {
        private String string;
        private IValue value;

        public StringIValue(String string, IValue value) {
            this.string = string;
            this.value = value;
        }

        public String getKey() {
            return this.string;
        }

        public IValue getValue() {
            return this.value;
        }

        public void setString(String string) {
            this.string = string;
        }

        public void setValue(IValue value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.string + " " + this.value;
        }
    }

    public static class LatchTableView {
        private int location;
        private int value;

        public LatchTableView(int address, int value) {
            this.location = address;
            this.value = value;
        }

        public int getLocation() {
            return this.location;
        }

        public int getValue() {
            return this.value;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.location + " " + this.value;
        }
    }
}
