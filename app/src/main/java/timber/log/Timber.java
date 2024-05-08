package timber.log;

import android.util.Log;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Timber {
    /* access modifiers changed from: private */
    public static final List<Tree> FOREST = new CopyOnWriteArrayList();
    private static final Tree TREE_OF_SOULS = new Tree() {
        public void v(String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).v(message, args);
            }
        }

        public void v(Throwable t, String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).v(t, message, args);
            }
        }

        public void d(String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).d(message, args);
            }
        }

        public void d(Throwable t, String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).d(t, message, args);
            }
        }

        public void i(String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).i(message, args);
            }
        }

        public void i(Throwable t, String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).i(t, message, args);
            }
        }

        public void w(String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).w(message, args);
            }
        }

        public void w(Throwable t, String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).w(t, message, args);
            }
        }

        public void e(String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).e(message, args);
            }
        }

        public void e(Throwable t, String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).e(t, message, args);
            }
        }

        public void wtf(String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).wtf(message, args);
            }
        }

        public void wtf(Throwable t, String message, Object... args) {
            List<Tree> forest = Timber.FOREST;
            int count = forest.size();
            for (int i = 0; i < count; i++) {
                forest.get(i).wtf(t, message, args);
            }
        }

        /* access modifiers changed from: protected */
        public void log(int priority, String tag, String message, Throwable t) {
            throw new AssertionError("Missing override for log method.");
        }
    };

    public static void v(String message, Object... args) {
        TREE_OF_SOULS.v(message, args);
    }

    public static void v(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.v(t, message, args);
    }

    public static void d(String message, Object... args) {
        TREE_OF_SOULS.d(message, args);
    }

    public static void d(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.d(t, message, args);
    }

    public static void i(String message, Object... args) {
        TREE_OF_SOULS.i(message, args);
    }

    public static void i(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.i(t, message, args);
    }

    public static void w(String message, Object... args) {
        TREE_OF_SOULS.w(message, args);
    }

    public static void w(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.w(t, message, args);
    }

    public static void e(String message, Object... args) {
        TREE_OF_SOULS.e(message, args);
    }

    public static void e(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.e(t, message, args);
    }

    public static void wtf(String message, Object... args) {
        TREE_OF_SOULS.wtf(message, args);
    }

    public static void wtf(Throwable t, String message, Object... args) {
        TREE_OF_SOULS.wtf(t, message, args);
    }

    public static Tree asTree() {
        return TREE_OF_SOULS;
    }

    public static Tree tag(String tag) {
        List<Tree> forest = FOREST;
        int count = forest.size();
        for (int i = 0; i < count; i++) {
            forest.get(i).explicitTag.set(tag);
        }
        return TREE_OF_SOULS;
    }

    public static void plant(Tree tree) {
        if (tree == null) {
            throw new NullPointerException("tree == null");
        } else if (tree == TREE_OF_SOULS) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        } else {
            FOREST.add(tree);
        }
    }

    public static void uproot(Tree tree) {
        if (!FOREST.remove(tree)) {
            throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
        }
    }

    public static void uprootAll() {
        FOREST.clear();
    }

    private Timber() {
        throw new AssertionError("No instances.");
    }

    public static abstract class Tree {
        /* access modifiers changed from: private */
        public final ThreadLocal<String> explicitTag = new ThreadLocal<>();

        /* access modifiers changed from: protected */
        public abstract void log(int i, String str, String str2, Throwable th);

        /* access modifiers changed from: package-private */
        public String getTag() {
            String tag = this.explicitTag.get();
            if (tag != null) {
                this.explicitTag.remove();
            }
            return tag;
        }

        public void v(String message, Object... args) {
            prepareLog(2, (Throwable) null, message, args);
        }

        public void v(Throwable t, String message, Object... args) {
            prepareLog(2, t, message, args);
        }

        public void d(String message, Object... args) {
            prepareLog(3, (Throwable) null, message, args);
        }

        public void d(Throwable t, String message, Object... args) {
            prepareLog(3, t, message, args);
        }

        public void i(String message, Object... args) {
            prepareLog(4, (Throwable) null, message, args);
        }

        public void i(Throwable t, String message, Object... args) {
            prepareLog(4, t, message, args);
        }

        public void w(String message, Object... args) {
            prepareLog(5, (Throwable) null, message, args);
        }

        public void w(Throwable t, String message, Object... args) {
            prepareLog(5, t, message, args);
        }

        public void e(String message, Object... args) {
            prepareLog(6, (Throwable) null, message, args);
        }

        public void e(Throwable t, String message, Object... args) {
            prepareLog(6, t, message, args);
        }

        public void wtf(String message, Object... args) {
            prepareLog(7, (Throwable) null, message, args);
        }

        public void wtf(Throwable t, String message, Object... args) {
            prepareLog(7, t, message, args);
        }

        /* access modifiers changed from: protected */
        public boolean isLoggable(int priority) {
            return true;
        }

        private void prepareLog(int priority, Throwable t, String message, Object... args) {
            if (isLoggable(priority)) {
                if (message != null && message.length() == 0) {
                    message = null;
                }
                if (message != null) {
                    if (args.length > 0) {
                        message = String.format(message, args);
                    }
                    if (t != null) {
                        message = message + "\n" + Log.getStackTraceString(t);
                    }
                } else if (t != null) {
                    message = Log.getStackTraceString(t);
                } else {
                    return;
                }
                log(priority, getTag(), message, t);
            }
        }
    }

    public static class DebugTree extends Tree {
        private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
        private static final int CALL_STACK_INDEX = 5;
        private static final int MAX_LOG_LENGTH = 4000;

        /* access modifiers changed from: protected */
        public String createStackElementTag(StackTraceElement element) {
            String tag = element.getClassName();
            Matcher m = ANONYMOUS_CLASS.matcher(tag);
            if (m.find()) {
                tag = m.replaceAll("");
            }
            return tag.substring(tag.lastIndexOf(46) + 1);
        }

        /* access modifiers changed from: package-private */
        public final String getTag() {
            String tag = super.getTag();
            if (tag != null) {
                return tag;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length > 5) {
                return createStackElementTag(stackTrace[5]);
            }
            throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }

        /* access modifiers changed from: protected */
        public void log(int priority, String tag, String message, Throwable t) {
            if (message.length() >= MAX_LOG_LENGTH) {
                int i = 0;
                int length = message.length();
                while (i < length) {
                    int newline = message.indexOf(10, i);
                    if (newline == -1) {
                        newline = length;
                    }
                    do {
                        int end = Math.min(newline, i + MAX_LOG_LENGTH);
                        String part = message.substring(i, end);
                        if (priority == 7) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }
                        i = end;
                    } while (i < newline);
                    i++;
                }
            } else if (priority == 7) {
                Log.wtf(tag, message);
            } else {
                Log.println(priority, tag, message);
            }
        }
    }
}
