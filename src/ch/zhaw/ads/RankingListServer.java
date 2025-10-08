package ch.zhaw.ads;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class RankingListServer implements CommandExecutor {

    public List<Competitor> createList(String rankingText) {
        // TODO Implement
        List<Competitor> list = new ArrayList<>();
        Scanner sc = new Scanner(rankingText);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(";");
            if (parts.length >= 2) {
                String name = parts[0].trim();
                String time = parts[1].trim();
                list.add(new Competitor(0, name, time));
            }
        }
        sc.close();
        return list;
    }

    public String createSortedText(List<Competitor> competitorList) {
        // TODO Implement

        Collections.sort(competitorList);

        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (Competitor c : competitorList) {
            c.setRank(rank++);
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }


    public String createNameList(List<Competitor> competitorList) {
        // TODO Implement

        Collections.sort(competitorList, new AlphaComparatorCompetitor());

        StringBuilder sb = new StringBuilder();
        for (Competitor c : competitorList) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }


    public String execute(String rankingList) {
        List<Competitor> competitorList = createList(rankingList);
        return "Rangliste\n" + createSortedText(competitorList);
    }
}
