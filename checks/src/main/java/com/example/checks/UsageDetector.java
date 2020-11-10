package com.example.checks;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.ClassContext;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.uast.UCallExpression;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.List;

public class UsageDetector extends Detector implements Detector.UastScanner {
    public static final Issue ISSUE = Issue.create(
            // ID: used in @SuppressLint warnings etc
            "xxxxx",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "Lint Mentions",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "This check highlights string literals in code which mentions " +
                    "the word `lint`. Blah blah blah.\n" +
                    "\n" +
                    "Another paragraph here.\n",
            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            new Implementation(
                    UsageDetector.class,
                    Scope.ALL));



    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("v", "d", "i", "w", "e", "wtf");
    }

    @Override
    public void visitMethod(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {
        if (context.getEvaluator().isMemberInClass(method, "android.util.Log")) {
            context.report(ISSUE, node, context.getLocation(node), "避免调用android.util.Log");
        }
    }



    @Override
    public void checkCall(@NotNull ClassContext context,
                          @NotNull ClassNode classNode, @NotNull MethodNode method, @NotNull MethodInsnNode call) {
        String name = classNode.name;
        String methodName = method.name;
        String callName = call.name;
        System.out.println("name="+name+",methodName="+methodName);

        context.report(ISSUE, context.getLocation(classNode), "dfjasdlfjadlfa", classNode);


    }
}
