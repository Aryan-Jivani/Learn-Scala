error id: A2A1135D93D55783FB6245AB5863A189
file:///c:/Users/jivan/OneDrive/Documents/Learning/TP/LearnScala/rockthejvm/Main.scala
### java.lang.NullPointerException: Cannot read the array length because "a" is null

occurred in the presentation compiler.



action parameters:
offset: 9
uri: file:///c:/Users/jivan/OneDrive/Documents/Learning/TP/LearnScala/rockthejvm/Main.scala
text:
```scala
object Ma@@

```


presentation compiler configuration:
Scala version: 2.12.20
Classpath:
<WORKSPACE>\.bloop\root\bloop-bsp-clients-classes\classes-Metals-8gs-P4s1SgGeEdrIzB4njA== [missing ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\sourcegraph\semanticdb-javac\0.12.3\semanticdb-javac-0.12.3.jar [exists ], <HOME>\.sbt\boot\scala-2.12.20\lib\scala-library.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb




#### Error stacktrace:

```
java.base/java.util.Arrays.sort(Arrays.java:1234)
	scala.tools.nsc.classpath.JFileDirectoryLookup.listChildren(DirectoryClassPath.scala:119)
	scala.tools.nsc.classpath.JFileDirectoryLookup.listChildren$(DirectoryClassPath.scala:103)
	scala.tools.nsc.classpath.DirectoryClassPath.listChildren(DirectoryClassPath.scala:314)
	scala.tools.nsc.classpath.DirectoryClassPath.listChildren(DirectoryClassPath.scala:314)
	scala.tools.nsc.classpath.DirectoryLookup.list(DirectoryClassPath.scala:84)
	scala.tools.nsc.classpath.DirectoryLookup.list$(DirectoryClassPath.scala:79)
	scala.tools.nsc.classpath.DirectoryClassPath.list(DirectoryClassPath.scala:314)
	scala.tools.nsc.classpath.AggregateClassPath.$anonfun$list$3(AggregateClassPath.scala:105)
	scala.collection.Iterator.foreach(Iterator.scala:943)
	scala.collection.Iterator.foreach$(Iterator.scala:943)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1431)
	scala.collection.IterableLike.foreach(IterableLike.scala:74)
	scala.collection.IterableLike.foreach$(IterableLike.scala:73)
	scala.collection.AbstractIterable.foreach(Iterable.scala:56)
	scala.tools.nsc.classpath.AggregateClassPath.list(AggregateClassPath.scala:101)
	scala.tools.nsc.util.ClassPath.list(ClassPath.scala:36)
	scala.tools.nsc.util.ClassPath.list$(ClassPath.scala:36)
	scala.tools.nsc.classpath.AggregateClassPath.list(AggregateClassPath.scala:30)
	scala.tools.nsc.symtab.SymbolLoaders$PackageLoader.doComplete(SymbolLoaders.scala:298)
	scala.tools.nsc.symtab.SymbolLoaders$SymbolLoader.complete(SymbolLoaders.scala:250)
	scala.reflect.internal.Symbols$Symbol.completeInfo(Symbols.scala:1542)
	scala.reflect.internal.Symbols$Symbol.info(Symbols.scala:1514)
	scala.reflect.internal.Types$TypeRef.decls(Types.scala:2283)
	scala.tools.nsc.typechecker.Namers$Namer.enterPackage(Namers.scala:766)
	scala.tools.nsc.typechecker.Namers$Namer.dispatch$1(Namers.scala:289)
	scala.tools.nsc.typechecker.Namers$Namer.standardEnterSym(Namers.scala:302)
	scala.tools.nsc.typechecker.AnalyzerPlugins.pluginsEnterSym(AnalyzerPlugins.scala:479)
	scala.tools.nsc.typechecker.AnalyzerPlugins.pluginsEnterSym$(AnalyzerPlugins.scala:478)
	scala.meta.internal.pc.MetalsGlobal$MetalsInteractiveAnalyzer.pluginsEnterSym(MetalsGlobal.scala:85)
	scala.tools.nsc.typechecker.Namers$Namer.enterSym(Namers.scala:280)
	scala.tools.nsc.typechecker.Analyzer$namerFactory$$anon$1.apply(Analyzer.scala:48)
	scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:465)
	scala.tools.nsc.Global$Run.$anonfun$compileLate$3(Global.scala:1657)
	scala.tools.nsc.Global$Run.$anonfun$compileLate$2(Global.scala:1657)
	scala.tools.nsc.Global$Run.$anonfun$compileLate$2$adapted(Global.scala:1656)
	scala.collection.Iterator.foreach(Iterator.scala:943)
	scala.collection.Iterator.foreach$(Iterator.scala:943)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1431)
	scala.tools.nsc.Global$Run.compileLate(Global.scala:1656)
	scala.tools.nsc.interactive.Global.parseAndEnter(Global.scala:654)
	scala.tools.nsc.interactive.Global.typeCheck(Global.scala:664)
	scala.meta.internal.pc.HoverProvider.typedHoverTreeAt(HoverProvider.scala:330)
	scala.meta.internal.pc.HoverProvider.hoverOffset(HoverProvider.scala:51)
	scala.meta.internal.pc.HoverProvider.hover(HoverProvider.scala:30)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$hover$1(ScalaPresentationCompiler.scala:474)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:148)
	scala.meta.internal.pc.CompilerAccess.$anonfun$withNonInterruptableCompiler$1(CompilerAccess.scala:132)
	scala.meta.internal.pc.CompilerAccess.$anonfun$onCompilerJobQueue$1(CompilerAccess.scala:209)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:152)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1583)
```
#### Short summary: 

java.lang.NullPointerException: Cannot read the array length because "a" is null