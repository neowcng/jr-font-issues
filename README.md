# demo project to illustrate the per-character font rendering problem

By default, jasperreport will use itext-2.1.7 for PDF rendering

This project will switch to itext-5 rendering due to client requirement
The following source code are adopted to use itext5 instead (for package under com.jaspersoft.*)
https://github.com/Jaspersoft/jasperreports-pdf-lib5

# Prequisite
- java 1.8

# Usage
use `gradlew test` to test the report output
the test will read the file `demo.txt` and render to the PDF using the jasper design file `remarks.jrxml`

# Motivation
For Chinese Character, the character set has been expanded to various CJK Extension in the unicode plane.
It is impossible to have a single font to have cover all the character.

In CSS, we can define a list of font to be used, which the font appear in the later sequence can be served as fallback
We want the same behavior for jasperreport PDF output.


# Expected output
the text should be rendered by these fonts, by the following preferring order:

* Noto Serif (for all latin characters)
* Noto Serif CJK TC (for all normal CJK characters)
* HanaMinA (for CJK characters cannot be found in Noto Serif CJK TC)
* HanaMinB (for CJK extension-B/C/D/...)
* HanaMinPlus (for CJK extension-B/C/D/...)

which roughly translate to this css
`font-family: 'Noto Serif', 'Noto Serif CJK TC', 'HanaMinA', 'HanaMinB', 'HanaMinPlus', serif`

Inside the `docs/`, it contains the output and diagnosis
You can use PDF-XChange Viewer to view the actual font specified
https://www.tracker-software.com/product/pdf-xchange-viewer


`jr-6-12-2-patched.pdf`
![docs/jr-6-12-2-patched.png] 
you can see that the text are rendered by 3 different fonts
abc -> Noto Serif
𨋢 -> HanaMinA
所有 -> Noto Serif CJK TC

`jr-6-12-2-orig.pdf`
![docs/jr-6-12-2-orig.png] 
abc -> Noto Serif
𨋢 所有 -> HanaMinA


# Fonts copyrights

Noto fonts
https://www.google.com/get/noto/

HanaMinA / HanaMinB / HanaMinPlus fonts
https://fonts.jp/hanazono/
