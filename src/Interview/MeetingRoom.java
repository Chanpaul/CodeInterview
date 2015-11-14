package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yongyangyu on 11/13/15.
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 */
public class MeetingRoom {
    public static class Interval {
        private int start;
        private int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public boolean catAttendMeetings(Interval[] intervals) {
        Comparator<Interval> comp = (a, b) -> {
            if (a.start < b.start) return -1;
            else if (a.start > b.start) return 1;
            else return 0;
        };
        Arrays.sort(intervals, comp);
        for (int i = 0; i < intervals.length-1; i ++) {
            if (intervals[i].end < intervals[i+1].start) continue;
            else return false;
        }
        return true;
    }

    /*
     * follow-up question:
     * Given an array of meeting time intervals consisting of start and end times
     * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
     *
     * For example,
     * Given [[0, 30],[5, 10],[15, 20]],
     * return 2.
     */
    public int minMeetingRooms(Interval[] intervals) {
        Comparator<Interval> comp = (a, b) -> {
            if (a.start < b.start) return -1;
            else if (a.start > b.start) return 1;
            else return 0;
        };
        Arrays.sort(intervals, comp);
        List<List<Interval>> rooms = new ArrayList<>();
        for (int i = 0; i < intervals.length; i ++) {
            int idx = findNoneOverlapping(rooms, intervals[i]);
            if (rooms.isEmpty() || idx == -1) {
                rooms.add(new ArrayList<>());
                rooms.get(rooms.size()-1).add(intervals[i]);
            }
            else {
                rooms.get(idx).add(intervals[i]);
            }
        }
        return rooms.size();
    }

    private int findNoneOverlapping(List<List<Interval>> rooms, Interval interval) {
        for (int i = 0; i < rooms.size(); i ++) {
            if (interval.start >= rooms.get(i).get(rooms.get(i).size()-1).end) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Interval[] intervals = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
        MeetingRoom mr = new MeetingRoom();
        System.out.println(mr.catAttendMeetings(intervals));
        System.out.println(mr.minMeetingRooms(intervals));
    }
}
