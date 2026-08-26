;$CmdLine[1] will be the first parameter passed, stored in "popup_name" varaible
;CmdLine[2] will be the second parameter passed, system downloaded gpg build file path
WinWaitActive("#32770",$CmdLine[1],2)
WinFlash($CmdLine[1]," ", 4, 500);
ControlSetText($CmdLine[1]," ","Edit1",$CmdLine[2]);
WinWaitActive("#32770",$CmdLine[1],2)
ControlClick($CmdLine[1]," ","Button1")