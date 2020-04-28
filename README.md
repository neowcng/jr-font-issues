##demo project to illustrate the per-character font rendering problem

By default, jasperreport will use itext-2.1.7 for PDF rendering

This project will switch to itext-5 rendering due to client requirement
The following source code are adopted to use itext5 instead (for package under com.jaspersoft.*)
https://github.com/Jaspersoft/jasperreports-pdf-lib5


#Prequisite
- java 1.8

#Usage
use `gradlew test` to test the report output
the test will read the file `demo.txt` and render to the PDF using the jasper design file `remarks.jrxml`


#Expected output
the text should be rendered by these fonts, by the following preferring order:

Noto Serif (for all latin characters)
Noto Serif CJK TC (for all normal CJK characters)
HanaMinA (for CJK character cannot be found in Noto Serif CJK TC)
HanaMinB (for CJK extension-B/C/D/...)
HanaMinPlus (for CJK extension-B/C/D/...)


#Fonts copyrights
Noto fonts
https://www.google.com/get/noto/

HanaMinA / HanaMinB / HanaMinPlus fonts
https://fonts.jp/hanazono/
