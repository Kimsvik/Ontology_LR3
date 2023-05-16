package bin;

import dto.Link;
import dto.SingleLineDiagram;

import java.util.HashMap;
import java.util.Map;


public class ElementsBinder {
    public static void bind(SingleLineDiagram sld) {
        Map<String, Link> linkIdToLinkMap = new HashMap<>();
        sld.getLinks().forEach(l -> linkIdToLinkMap.put(l.getId(), l));

        /** Из sld берем весь список Elements и идем по каждому элементу
         *  в каждом элементе идем по всем портам.
         */

        sld.getElements().forEach(element -> element.getPorts().forEach(p -> {

            if (p.getLinks().isEmpty()) return;
            String linkId = p.getLinks().get(0);        // записываем ИД линка каждого порта в linkId
            if (linkId == null) return;

            // связь порта с линком
            Link link = linkIdToLinkMap.get(linkId);    // по свойству словарей берем линк (value) с нужным значением Id (key)
            p.setLink(link);                            // в Port загружаем адрес линка к которому от подключен (связываем)

            // связь линка с портом
            if (link.getSourcePortId().equals(p.getId())) { // смотрим сходится ли Id порта с источником Линка
                link.setSourcePort(p);      // Id порта элемента, SourceId это Id самого элемента (линии, шины ...)
                link.setSource(element);
            } else {                                        // или сходится с целью (Target) линка  ( у линка есть цель и источник которые она связывает, это порты)
                link.setTargetPort(p);
                link.setTarget(element);
            }

        }));
    }
}
