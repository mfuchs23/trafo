; herold.nsi
;--------------------------------

;Include Modern UI

!include "MUI.nsh"

; The name of the installer
Name "herold"

; The file to write
OutFile @OutFile@

; The default installation directory
InstallDir "$PROGRAMFILES\Herold"

;--------------------------------
;Interface Settings

!define MUI_ABORTWARNING
!define MUI_ICON "root\Programme\herold\icons\48x48\herold.ico"
!define MUI_UNICON "root\Programme\herold\icons\48x48\herold.ico"

;--------------------------------
;Pages

!insertmacro MUI_PAGE_LICENSE "root\Programme\herold\COPYING"
; !insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
  
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
;Languages

!insertmacro MUI_LANGUAGE "Arabic"
!insertmacro MUI_LANGUAGE "Bulgarian"
!insertmacro MUI_LANGUAGE "Croatian"
!insertmacro MUI_LANGUAGE "Czech"
!insertmacro MUI_LANGUAGE "Danish"
!insertmacro MUI_LANGUAGE "Dutch"
!insertmacro MUI_LANGUAGE "English"
!insertmacro MUI_LANGUAGE "Estonian"
!insertmacro MUI_LANGUAGE "Farsi"
!insertmacro MUI_LANGUAGE "Finnish"
!insertmacro MUI_LANGUAGE "French"
!insertmacro MUI_LANGUAGE "German"
!insertmacro MUI_LANGUAGE "Greek"
!insertmacro MUI_LANGUAGE "Hebrew"
!insertmacro MUI_LANGUAGE "Hungarian"
!insertmacro MUI_LANGUAGE "Indonesian"
!insertmacro MUI_LANGUAGE "Italian"
!insertmacro MUI_LANGUAGE "Japanese"
!insertmacro MUI_LANGUAGE "Korean"
!insertmacro MUI_LANGUAGE "Latvian"
!insertmacro MUI_LANGUAGE "Lithuanian"
!insertmacro MUI_LANGUAGE "Macedonian"
!insertmacro MUI_LANGUAGE "Norwegian"
!insertmacro MUI_LANGUAGE "Polish"
!insertmacro MUI_LANGUAGE "Portuguese"
!insertmacro MUI_LANGUAGE "PortugueseBR"
!insertmacro MUI_LANGUAGE "Romanian"
!insertmacro MUI_LANGUAGE "Russian"
!insertmacro MUI_LANGUAGE "Serbian"
!insertmacro MUI_LANGUAGE "SimpChinese"
!insertmacro MUI_LANGUAGE "Slovak"
!insertmacro MUI_LANGUAGE "Slovenian"
!insertmacro MUI_LANGUAGE "Spanish"
!insertmacro MUI_LANGUAGE "Swedish"
!insertmacro MUI_LANGUAGE "Thai"
!insertmacro MUI_LANGUAGE "TradChinese"
!insertmacro MUI_LANGUAGE "Turkish"
!insertmacro MUI_LANGUAGE "Ukrainian"
 
;--------------------------------

; The stuff to install
Section herold

  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  SetShellVarContext "all"

  CreateDirectory "$SMPROGRAMS\Herold"

  CreateShortCut '$DESKTOP\Herold.lnk' "$INSTDIR\doc\html\index.html" "" '$INSTDIR\icons\48x48\herold.ico'
  CreateShortCut '$SMPROGRAMS\Herold\Herold (HTML).lnk' "$INSTDIR\doc\html\index.html" "" '$INSTDIR\icons\48x48\herold.ico'
  CreateShortCut '$SMPROGRAMS\Herold\Herold (PDF).lnk' "$INSTDIR\doc\tutorial.pdf" "" '$INSTDIR\icons\48x48\herold.ico'
  CreateShortCut "$SMPROGRAMS\Herold\Uninstall.lnk" "$INSTDIR\Uninstall.exe" "" "$INSTDIR\Uninstall.exe" 0

  File /r "root\Programme\herold\*.*"
  
  FileOpen  $0 "$INSTDIR\bin\herold.bat" w
  FileWrite $0 "@echo off$\r$\n"
  FileWrite $0 "set HEROLD_HOME=$INSTDIR$\r$\n"
  FileWrite $0 "REM mode con:cols=80 lines=25$\r$\n"
  FileWrite $0 "java -Xmx1024m -Dherold.home=$\"%HEROLD_HOME%$\" -Dconsole.lineWidth=80 -jar $\"%HEROLD_HOME%\jars\herold.jar$\" %*$\r$\n"
  FileClose $0

  FileOpen  $1 "$SYSDIR\herold.bat" w
  FileWrite $1 "@echo off$\r$\n"
  FileWrite $1 "set HEROLD_HOME=$INSTDIR$\r$\n"
  FileWrite $0 "REM mode con:cols=80 lines=25$\r$\n"
  FileWrite $0 "java -Xmx1024m -Dherold.home=$\"%HEROLD_HOME%$\" -Dconsole.lineWidth=80 -jar $\"%HEROLD_HOME%\jars\herold.jar$\" %*$\r$\n"
  FileClose $1
 
  WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\Herold" "DisplayName" "Herold"
  WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\Herold" "UninstallString" "$INSTDIR\Uninstall.exe"
  WriteUninstaller "Uninstall.exe"

SectionEnd 

; Uninstall section

Section Uninstall
    
    SetShellVarContext "all"

    Delete '$DESKTOP\Herold.lnk'
    RMDir /r "$SMPROGRAMS\Herold"
    RMDir /r "$INSTDIR"

SectionEnd

Function .onInit

  !insertmacro MUI_LANGDLL_DISPLAY

FunctionEnd

Function Preinstall

  ReadRegStr $R0 HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\Herold" "UninstallString"
  Done:

FunctionEnd
