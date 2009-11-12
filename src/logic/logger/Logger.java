package logic.logger;

abstract class Logger
{
    public static int ERR = 3;
    public static int NOTICE = 5;
    public static int DEBUG = 7;
    protected int mask;

    // The next element in the chain of responsibility
    protected Logger next;
    public Logger setNext( Logger l )
    {
        next = l;
        return l;
    }

    public void message( String msg, int priority )
    {
        if ( priority <= mask )
        {
            writeMessage( msg );
        }
        if ( next != null )
        {
            next.message( msg, priority );
        }
    }

    abstract protected void writeMessage( String msg );

}


