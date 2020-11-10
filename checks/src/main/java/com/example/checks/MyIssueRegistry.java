package com.example.checks;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

public class MyIssueRegistry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                UsageDetector.ISSUE
        );
    }

    @Override
    public int getMinApi() {
        return 1;
    }

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }
} 
