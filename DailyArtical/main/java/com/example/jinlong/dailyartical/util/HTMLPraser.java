package com.example.jinlong.dailyartical.config;

import com.example.jinlong.dailyartical.bean.Artical;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JinLong on 2015/2/8.
 * Html解析成文章类
 */
public class HTMLPraser {
    public static Artical getArticalsByHtml(StringBuilder builder)
            throws ParserException, MalformedURLException, IOException {

        Parser parser = Parser.createParser(builder.toString(), "UTF-8");
        List<Artical> list = new ArrayList<Artical>();
        parser.setEncoding("UTF-8");

        // 创建HasAttributeFilter实例
        NodeFilter filter1 = new HasAttributeFilter("id");
        // 创建TagNameFilter实例
        NodeFilter innerFilter = new TagNameFilter("DIV");
        // 创建HasChildFilter实例
        NodeFilter filter2 = new HasChildFilter(innerFilter);
        // 创建AndFilter实例

        NodeFilter filter = new AndFilter(filter1, filter2);
        // 筛选出所有具有id属性且拥有子节点的所有DIV节点

        NodeList nodes = parser.extractAllNodesThatMatch(filter);
        if (nodes != null) {

            Node textnode = (Node) nodes.elementAt(0);
            NodeList children = textnode.getChildren();
            Node nodeTitle = (Node) children.elementAt(3);
            Node nodeAuthor = (Node) children.elementAt(5).getFirstChild();
            NodeList nodeContent = (NodeList) children.elementAt(7)
                    .getChildren();

            String title = nodeTitle.toHtml(true);
            String author = nodeAuthor.toHtml(true);

            StringBuilder stringbuilder = new StringBuilder();

            for (int i = 0; i < nodeContent.size(); i++) {
                String tmp = (String) nodeContent.elementAt(i).toHtml(true);
                stringbuilder.append(tmp);

            }

            String content = stringbuilder.toString();
            list.add(new Artical(title, author, content));

        }

        return list.get(0);
    }
}
