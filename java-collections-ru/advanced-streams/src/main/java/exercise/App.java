package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static void main(String[] args) {
        String text = "[foo:bar]\n" +
                "environment=\"X_FORWARDED_var1=111\"\n" +
                "\n" +
                "[bar:baz]\n" +
                "environment=\"key2=true,X_FORWARDED_var2=123\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "\n" +
                "[baz:foo]\n" +
                "key3=\"value3\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "\n" +
                "[program:prepare]\n" +
                "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"\n" +
                "\n" +
                "[program:start]\n" +
                "environment=\"pwd=/temp,user=tirion\"\n" +
                "\n" +
                "[program:options]\n" +
                "environment=\"X_FORWARDED_mail=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make environment'\n" +
                "\n" +
                "[empty]\n" +
                "command=\"X_FORWARDED_HOME=/ cd ~\"";
        System.out.println(getForwardedVariables(text));
        }
    public static String getForwardedVariables(String config) {
        List<String> list = Arrays.stream(config.split("\n"))
                .filter(line1 -> line1.startsWith("environment"))
                .filter(line2 -> line2.contains("X_FORWARDED"))
                .map(line3 -> line3.substring("environment=\"".length()))
                .flatMap(text -> Stream.of(text.split(",")))
                .filter(line1 -> line1.startsWith("X_FORWARDED_"))
                .map(line3 -> line3.substring("X_FORWARDED_".length()))
                .flatMap(text -> Stream.of(text.split("\"")))
                .collect(Collectors.toList());
        return String.join(",", list);
    }
}
//END
