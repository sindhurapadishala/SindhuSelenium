;Wait for dialog box and click OK button
_WaitAndClick()

Func _WaitAndClick()
     WinWait("[CLASS:MozillaDialogClass]", "", 50)
     WinActivate("[CLASS:MozillaDialogClass]")
     WinFlash("[CLASS:MozillaDialogClass]", "", 3, 500)
     Sleep(3000)
	 Send("{ALTDOWN}s{ALTUP}")
	  Sleep(3000)
	  ControlFocus( "MozillaDialogClass", "", "" )
     Send("{ENTER}")
EndFunc