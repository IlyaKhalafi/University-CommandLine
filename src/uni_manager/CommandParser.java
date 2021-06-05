package uni_manager;

import java.util.HashMap;

class CommandParser {

    private String input[], args[], comm, error;
    private HashMap<String, String> hashed;

    private static HashMap<String, String> commands = new HashMap<String, String>() {
        {
            put("add student", "name family -y year");
            put("list student", "-p prefix");
            put("tuition student", "number sem");
            put("add prof", "username name family");
            put("salary prof", "username -s sems");
            put("list prof", "");
            put("profit", "sem");
            put("add course", "name username sem -w weight");
            put("course list", "-p username -s sem");
            put("assign course", "name username sem number");
            put("show course", "number sem");
            put("remove course", "name username sem number");
            put("assign grade", "name username sem number grade");
            put("average", "number sem");
            put("ranks", "sem");
        }
    };

    public CommandParser(String input) {
        for( String key : commands.keySet() )
            if( input.startsWith(key) ) {
                this.comm = key;
                this.input = input.substring(key.length()).trim().split("[ \\:]"); // User Input
                this.args = commands.get(key).split(" "); // Standard Input
                this.hashed = this.MakeHash();
            }
        if( this.input == null )
            this.error = "INVALID COMMAND";
    }

    private HashMap<String, String> MakeHash() {
        HashMap<String, String> corr = new HashMap<>();
        int pnt_args = 0, pnt_input = 0;
        while( pnt_args < this.args.length ) {

            if( pnt_input < input.length && input[pnt_input].length() < 1 )
                pnt_input++;

            if( pnt_args < args.length && args[pnt_args].length() < 1 )
                pnt_args++;

            else if( pnt_input >= this.input.length ) {
                if( !this.args[pnt_args].startsWith("-") )
                    this.error = "missing argument: " + this.args[pnt_args];
                return corr;
            }

            else if( !this.input[pnt_input].startsWith("-") && this.args[pnt_args].startsWith("-") ) {
                corr.put(this.args[++pnt_args], null);
                pnt_args++;
            }

            else if( this.input[pnt_input].startsWith("-") && !this.args[pnt_args].startsWith("-") ) {
                this.error = "missing argument: " + this.args[pnt_args];
                return null;
            }

            else if( !this.input[pnt_input].startsWith("-") && !this.args[pnt_args].startsWith("-") )
                corr.put(this.args[pnt_args++], this.input[pnt_input++]);

            else if( this.input[pnt_input].startsWith("-") && this.args[pnt_args].startsWith("-") && !this.args[pnt_args].equals(this.input[pnt_input]) ) {
                corr.put(this.args[pnt_args+1], null);
                pnt_args += 2;
            }

            else if( this.input[pnt_input].startsWith("-") && this.args[pnt_args].startsWith("-") && this.args[pnt_args].equals(this.input[pnt_input]) ) {
                String val = ""; pnt_args++; pnt_input++;

                while( pnt_input < input.length && !this.input[pnt_input].startsWith("-") )
                    val += this.input[pnt_input++] + " ";

                corr.put(this.args[pnt_args++], val.trim());
            }
        }
        return corr;
    }

    private String get(String name) { return hashed.get(name); }

    public void Launch() {

        if( this.comm.equals("add student") )
            CommandExecuter.AddStudent(get("name"), get("family"), get("year") == null ? 99 : Integer.parseInt(get("year")) );

        else if( this.comm.equals("list student") )
            CommandExecuter.ListStudent(get("prefix"));

        else if( this.comm.equals("tuition student") )
            CommandExecuter.TuitionStudent(get("number"), get("sem"));

        else if( this.comm.equals("add prof") )
            CommandExecuter.AddProf(get("username"), get("name"), get("family"));

        else if( this.comm.equals("salary prof") )
            CommandExecuter.SalaryProf(get("username"), get("sems"));

        else if( this.comm.equals("list prof") )
            CommandExecuter.ListProf();

        else if( this.comm.equals("profit") )
            CommandExecuter.Profit(get("sem"));

        else if( this.comm.equals("add course") )
            CommandExecuter.AddCourse(get("name"), get("username"), get("sem"), get("weight") == null ? 3 : Integer.parseInt(get("weight")));

        else if( this.comm.equals("course list") )
            CommandExecuter.CourseList(get("username"), get("sem"));

        else if( this.comm.equals("assign course") )
            CommandExecuter.AssignCourse(get("name"), get("username"), get("sem"), get("number"));

        else if( this.comm.equals("show course") )
            CommandExecuter.ShowCourse(get("number"), get("sem"));

        else if( this.comm.equals("remove course") )
            CommandExecuter.RemoveCourse(get("name"), get("username"), get("sem"), get("number"));

        else if( this.comm.equals("assign grade") )
            CommandExecuter.AssignGrade(get("name"), get("username"), get("sem"), get("number"), Double.parseDouble(get("grade")));

        else if( this.comm.equals("average") )
            CommandExecuter.Average(get("number"), get("sem"));

        else if( this.comm.equals("ranks") )
            CommandExecuter.Ranks(get("sem"));
    }

    public String getError() {
        return this.error;
    }

}