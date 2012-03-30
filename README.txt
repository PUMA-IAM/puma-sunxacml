This file contains notes to dev team related to the project.

* To make test files validatable:

diff -r 73c68c0935ed src/test/resources/conformance-tests/IIIC001Request.xml
--- a/src/test/resources/conformance-tests/IIIC001Request.xml   Thu May 06 07:29:37 2010 -0400
+++ b/src/test/resources/conformance-tests/IIIC001Request.xml   Wed May 12 11:22:51 2010 -0400
@@ -3,7 +3,7 @@
       xmlns="urn:oasis:names:tc:xacml:2.0:context:schema:os"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="urn:oasis:names:tc:xacml:2.0:context:schema:os
-        access_control-xacml-2.0-context-schema-os.xsd">
+        ../../../../src/main/resources/access_control-xacml-2.0-context-schema-os.xsd">
     <Subject>
         <Attribute
               AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id"

* To debug ConformanceTest in NB use Debug Test File in editor window for ConformanceTest


