
Frequently Asked Questions
==========================
This page lists some of the more frequently asked questions we get,
and answers that we hope will prove useful. If you don't see an answer
to your question here, then check out the programmer's guide and the
javadocs. If that fails, try sending mail to the `discussion list
<mailto:sunxacml-discuss@lists.sourceforge.net>`__ or posting on the
discussion forums.


XACML
-----

+ :ref:`What is XACML? <xaml-faqitem-1>`
+ :ref:`Are people using XACML? <xaml-faqitem-2>`
+ :ref:`What are PDPs and PEPs? <xaml-faqitem-3>`
+ :ref:`How do PDPs and PEPs communicate? <xaml-faqitem-4>`
+ :ref:`What's a resource hierarchy? <xaml-faqitem-5>`
+ :ref:`Where do I find tools? <xaml-faqitem-6>`

Sun's XACML Implementation
--------------------------

+ :ref:`What is this project? <sunxaml-faqitem-1>`
+ :ref:`What's with the name? <sunxaml-faqitem-2>`
+ :ref:`Why aren't my policy references working? <sunxaml-faqitem-3>`
+ :ref:`What's a finder module? <sunxaml-faqitem-4>`
+ :ref:`How do I find an attribute value based on another attribute value? <sunxaml-faqitem-5>`
+ :ref:`Why aren't more modules included? <sunxaml-faqitem-6>`
+ :ref:`How do I retrieve policies from my directory/database/registry/other? <sunxaml-faqitem-7>`

.. _xaml-faqitem-1:

**Q: What is XACML?**

**A:** XACML, the eXtensible Access Control Markup
Language, is an OASIS standard. Originally ratified in Februrary 2003,
it is currently at version 1.1 and a final 2.0 specification is
expected at the end of summer 2004. XACML is a standard language for
expressing access control, or authorization, policy, and a standard
format for expressing queries over these policies. For a high-level
description of XACML, look at the first section of the `programmer's
guide <guide.html>`__.

.. _xaml-faqitem-2:

**Q: Are people using XACML?**

**A:** Yes! This project has worked its way
into many commercial and experimental systems, as have other
implementations. A new testimonials section of this web site is still
under development, but when it's ready, that will give you a better
idea of who is using XACML today.

.. _xaml-faqitem-3:

**Q: What are PDPs and PEPs?**

**A:** These are two of the core conceptual
elements of the XACML model. A PDP, or Policy Decision Point, is the
processing engine that understands how to evaluate policies based on
Requests. A PEP, or Policy Enforcement Point, is the (typically)
application-specific element that is physically enforcing access to a
resource, and that will generate Requests on a PDP. Note that this
language was not invented for XACML. It actually comes from IETF and
DMTF specifications, and represents a common way of abstracting the
different functional components in an authorization system.

.. _xaml-faqitem-4:

**Q: How do PDPs and PEPs communicate?**

**A:** This depends on your model.
In some systems the PDP and PEP are actually co-located in the same
application. In other systems they may be separate but still on the
same machine, or they may be services available over a network. In any
of these cases they may choose to use the standard Request and
Response formats or some custom representation. Currently, there are
no standards for doing online communication bewteen a PEP and a PDP,
but the current drafts for XACML 2.0 and SAML 2.0 adress this problem.

.. _xaml-faqitem-5:

**Q: What's a resource hierarchy?**

**A:** XACML 1.x defines a hierarchy
simply as a resource and all its direct children or all its
descendants at any depth. This is a tree-based view that can be used
to describe filesystems, XML documents, LDAP directories, or any other
system organized as a tree. By specifying a scope attribute in a
Request, you can specify that you're requesting access to multiple
resources in a hiearchy. In XACML 2.0 this feature has been expanded
significantly.

.. _xaml-faqitem-6:

**Q: Where do I find tools?**

**A:** Currently, there are very few tools
available for working with XACML (ie, authoring policies, debuging
policies, visualizing policies and all the P*P elements, etc.), and
most of those are in the early experimental stage. This includes a
tool being developed alongside this project (sorry, it's not available
yet). As this situation changes, we'll try to keep this page up-to-
date.

.. _sunxaml-faqitem-1:

**Q: What is this project?**

**A:** This is a complete implementation of
XACML 1.x, written in the Java TM programming language (it requires
verion 1.4.0 or later). Interfaces are provided for creating requests,
handling and evaluating policies, and extending the system to support
new fetures and to interact with other services. The bundled releases
include extensive guides, samples, and XACML examples.

.. _sunxaml-faqitem-2:

**Q: What's with the name?**

**A:** This project was originally created in
Sun Microsystem's Research Laboratories by members of the Internet
Security Research Group. Ok, so it's not the most creative name.
Still, we're hoping that you're more interested in the functionality
than the name.

.. _sunxaml-faqitem-3:

**Q: Why aren't my policy references working?**

**A:** While Policy and
PolicySet references are a standard feature of the XACML
specification, there is no standard way to follow a reference to the
policy it references. A reference may be any identifier, so the PDP
has no way to know what it means. Since the standard doesn't provide
any ways to retrieve referenced policies, there's no default support
provided in this project. The good news is that it's pretty easy for
you to build this support, simply by implementing a PolicyFinderModule
that handles references. The sample code in the packaged releases
shows you how to do this.

.. _sunxaml-faqitem-4:

**Q: What's a finder module?**

**A:** The term "finder module," or just
"module," is used in this project to mean a custom class that can be
plugged into a PDP to support retrieval of policies, attribute values,
or hierarcical resource information. This system lets you work with
policies in arbitrary locations, pull attribute values from any source
during evaluation, etc. The `com.sun.xacml.finder` package contains
all the classes you need initially, and there are good examples in
both the programmer's guide and the packaged releases to show you how
to write your own module. This project actually provides a few modules
to get you started. These modules either implement standard
functionality or are provided to help you get familiar with XACML and
this implementation.

.. _sunxaml-faqitem-5:

**Q: How do I find an attribute value based on another attribute
value?**

**A:** Implement an AttributeFinderModule, and in your findAttribute
method use the EvaluationCtx to search for other values. This is a
common thing to do, for instance if the user's name is provided and
you want to lookup what groups they're a member of. There is an
example in the sample code that shows how to do exactly this.

.. _sunxaml-faqitem-6:

**Q: Why aren't more modules included?**

**A:** Basically, this project
sticks very closely to the standard; if it's not in the XACML
standard, then it's probably not here. Too many custom modules would
encourage implementations that weren't actually interoperable or based
on any open standards. As profiles define standard functionality, that
will be supported here. If you have a specific module that you'd like
to see supported here, however, please let us know. If a critical mass
of these are requested, then we could setup a new section of the CVS
tree to support "experimental" modules.

.. _sunxaml-faqitem-7:

**Q: How do I retrieve policies from my
directory/database/registry/other?**

**A:** Just like there's no standard for
communicating between a PEP and a PDP, there's no standard for
retrieving policies from any source. As explained in the previous
question, there is therefore no default support for working with
specific policy sources. There are, however, pretty good examples in
the releases of how to add support, and the developers on this project
are always will to help you figure this out.


Copyright 2003-2004 Sun Microsystems, Inc. All rights reserved. Use is
subject to license terms.


Sun, Sun Microsystems, the Sun Logo, and Java are trademarks or
registered trademarks of Sun Microsystems, Inc. in the US and other
countries.


Last Updated On: July 16, 2004




















