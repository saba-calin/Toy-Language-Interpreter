package view;

import controller.Controller;
import exceptions.InputException;
import model.expressions.ArithmeticExpression;
import model.expressions.ArithmeticOperation;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.statements.filestatements.CloseFileStatement;
import model.statements.filestatements.OpenFileStatement;
import model.statements.filestatements.ReadFileStatement;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private void printMenu() {
        System.out.println("Enter the statement you would like to execute");
        System.out.println("1. int v; v=2; print(v)");
        System.out.println("2. int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)");
        System.out.println("3. bool a; int v; a = true; IF(a) THEN {v = 2} ELSE {v = 3}; print(v)");
        System.out.println("4. test file operations");
        System.out.print(">");
    }

    public void run() {
        printMenu();
        int option = readInteger();

        IStatement statement;
        try {
            if (option == 1) {  // int v; v=2; print(v)
                statement = new CompositeStatement(
                                new VarDeclStatement("v", new IntType()),
                                new CompositeStatement(
                                    new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                    new PrintStatement(new VariableExpression("v"))
                                )
                            );
            }
            else if (option == 2) {  // int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)
                statement = new CompositeStatement(
                                new VarDeclStatement("a", new IntType()),
                                new CompositeStatement(
                                    new VarDeclStatement("b", new IntType()),
                                    new CompositeStatement(
                                        new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), ArithmeticOperation.MULTIPLY), ArithmeticOperation.ADD)),
                                        new CompositeStatement(
                                            new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), ArithmeticOperation.ADD)),
                                            new PrintStatement(new VariableExpression("b"))
                                        )
                                    )
                                )
                            );
            }
            else if (option == 3) {  // bool a; int v; a = true; IF(a) THEN {v = 2} ELSE {v = 3}; print(v)
                statement = new CompositeStatement(
                                new VarDeclStatement("a", new BoolType()),
                                new CompositeStatement(
                                    new VarDeclStatement("v", new IntType()),
                                    new CompositeStatement(
                                        new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                        new CompositeStatement(
                                            new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                            new PrintStatement(new VariableExpression("v"))
                                        )
                                    )
                                )
                            );
            }
            else if (option == 4) {
                statement = new CompositeStatement(
                        new VarDeclStatement("varf", new StringType()),
                        new CompositeStatement(
                            new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                            new CompositeStatement(
                                new OpenFileStatement(new VariableExpression("varf")),
                                new CompositeStatement(
                                    new VarDeclStatement("varc", new IntType()),
                                    new CompositeStatement(
                                        new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                        new CompositeStatement(
                                            new PrintStatement(new VariableExpression("varc")),
                                            new CompositeStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompositeStatement(
                                                    new PrintStatement(new VariableExpression("varc")),
                                                    new CloseFileStatement(new VariableExpression("varf"))
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                );
            }
            else {
                throw new InputException("Invalid option");
            }

            this.controller.addStatement(statement);
            this.controller.allSteps();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private int readInteger() {
        int ans = 0;
        Scanner scanner = new Scanner(System.in);

        boolean ok = true;
        while (ok) {
            try {
                ans = scanner.nextInt();
                ok = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid integer; Try again");
                scanner.nextLine();
            }
        }

        return ans;
    }
}
