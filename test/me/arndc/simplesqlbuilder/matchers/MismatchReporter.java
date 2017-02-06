package me.arndc.simplesqlbuilder.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

final class MismatchReporter {
    static void reportMismatch(String name,
                               Matcher<?> matcher,
                               Object item,
                               Description mismatchDescription,
                               boolean firstMismatch) {
        if (!firstMismatch)
            mismatchDescription.appendText(", ");

        mismatchDescription.appendText(name).appendText(" ");
        matcher.describeMismatch(item, mismatchDescription);
    }
}
