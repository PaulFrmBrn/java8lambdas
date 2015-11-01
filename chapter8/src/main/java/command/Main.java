package command;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Command");

        Editor editor = new EditorImpl();

        // old way
        Macro macro = new Macro();
        macro.record(new Open(editor));
        macro.record(new Save(editor));
        macro.record(new Close(editor));
        macro.run();

        // new way: lambdas
        // classes Open, Save, Close - are redundant
        Macro macroNew = new Macro();
        macroNew.record(editor::open);
        macroNew.record(editor::save);
        macroNew.record(editor::close);
        macroNew.run();


    }

}
