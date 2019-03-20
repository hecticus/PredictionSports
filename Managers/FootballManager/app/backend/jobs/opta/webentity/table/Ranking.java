
package backend.jobs.opta.webentity.table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rank",
    "rankStatus",
    "rankId",
    "lastRank",
    "contestantId",
    "contestantName",
    "contestantShortName",
    "contestantClubName",
    "contestantCode",
    "points",
    "matchesPlayed",
    "matchesWon",
    "matchesLost",
    "matchesDrawn",
    "goalsFor",
    "goalsAgainst",
    "goaldifference",
    "venueId",
    "venueName",
    "minimumAttendance",
    "maximumAttendance",
    "averageAttendance",
    "capacity",
    "percentSold",
    "goals0",
    "goals1",
    "goals2",
    "goals3",
    "goals4",
    "goals5",
    "goals6",
    "goals7",
    "goalsMoreThan7",
    "goalsAverage"
})
public class Ranking {

    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("rankStatus")
    private String rankStatus;
    @JsonProperty("rankId")
    private String rankId;
    @JsonProperty("lastRank")
    private Integer lastRank;
    @JsonProperty("contestantId")
    private String contestantId;
    @JsonProperty("contestantName")
    private String contestantName;
    @JsonProperty("contestantShortName")
    private String contestantShortName;
    @JsonProperty("contestantClubName")
    private String contestantClubName;
    @JsonProperty("contestantCode")
    private String contestantCode;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("matchesPlayed")
    private Integer matchesPlayed;
    @JsonProperty("matchesWon")
    private Integer matchesWon;
    @JsonProperty("matchesLost")
    private Integer matchesLost;
    @JsonProperty("matchesDrawn")
    private Integer matchesDrawn;
    @JsonProperty("goalsFor")
    private Integer goalsFor;
    @JsonProperty("goalsAgainst")
    private Integer goalsAgainst;
    @JsonProperty("goaldifference")
    private String goaldifference;
    @JsonProperty("venueId")
    private String venueId;
    @JsonProperty("venueName")
    private String venueName;
    @JsonProperty("minimumAttendance")
    private Integer minimumAttendance;
    @JsonProperty("maximumAttendance")
    private Integer maximumAttendance;
    @JsonProperty("averageAttendance")
    private Integer averageAttendance;
    @JsonProperty("capacity")
    private Integer capacity;
    @JsonProperty("percentSold")
    private String percentSold;
    @JsonProperty("goals0")
    private Integer goals0;
    @JsonProperty("goals1")
    private Integer goals1;
    @JsonProperty("goals2")
    private Integer goals2;
    @JsonProperty("goals3")
    private Integer goals3;
    @JsonProperty("goals4")
    private Integer goals4;
    @JsonProperty("goals5")
    private Integer goals5;
    @JsonProperty("goals6")
    private Integer goals6;
    @JsonProperty("goals7")
    private Integer goals7;
    @JsonProperty("goalsMoreThan7")
    private Integer goalsMoreThan7;
    @JsonProperty("goalsAverage")
    private String goalsAverage;

    @JsonProperty("rank")
    public Integer getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonProperty("rankStatus")
    public String getRankStatus() {
        return rankStatus;
    }

    @JsonProperty("rankStatus")
    public void setRankStatus(String rankStatus) {
        this.rankStatus = rankStatus;
    }

    @JsonProperty("rankId")
    public String getRankId() {
        return rankId;
    }

    @JsonProperty("rankId")
    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    @JsonProperty("lastRank")
    public Integer getLastRank() {
        return lastRank;
    }

    @JsonProperty("lastRank")
    public void setLastRank(Integer lastRank) {
        this.lastRank = lastRank;
    }

    @JsonProperty("contestantId")
    public String getContestantId() {
        return contestantId;
    }

    @JsonProperty("contestantId")
    public void setContestantId(String contestantId) {
        this.contestantId = contestantId;
    }

    @JsonProperty("contestantName")
    public String getContestantName() {
        return contestantName;
    }

    @JsonProperty("contestantName")
    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    @JsonProperty("contestantShortName")
    public String getContestantShortName() {
        return contestantShortName;
    }

    @JsonProperty("contestantShortName")
    public void setContestantShortName(String contestantShortName) {
        this.contestantShortName = contestantShortName;
    }

    @JsonProperty("contestantClubName")
    public String getContestantClubName() {
        return contestantClubName;
    }

    @JsonProperty("contestantClubName")
    public void setContestantClubName(String contestantClubName) {
        this.contestantClubName = contestantClubName;
    }

    @JsonProperty("contestantCode")
    public String getContestantCode() {
        return contestantCode;
    }

    @JsonProperty("contestantCode")
    public void setContestantCode(String contestantCode) {
        this.contestantCode = contestantCode;
    }

    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    @JsonProperty("matchesPlayed")
    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    @JsonProperty("matchesPlayed")
    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @JsonProperty("matchesWon")
    public Integer getMatchesWon() {
        return matchesWon;
    }

    @JsonProperty("matchesWon")
    public void setMatchesWon(Integer matchesWon) {
        this.matchesWon = matchesWon;
    }

    @JsonProperty("matchesLost")
    public Integer getMatchesLost() {
        return matchesLost;
    }

    @JsonProperty("matchesLost")
    public void setMatchesLost(Integer matchesLost) {
        this.matchesLost = matchesLost;
    }

    @JsonProperty("matchesDrawn")
    public Integer getMatchesDrawn() {
        return matchesDrawn;
    }

    @JsonProperty("matchesDrawn")
    public void setMatchesDrawn(Integer matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    @JsonProperty("goalsFor")
    public Integer getGoalsFor() {
        return goalsFor;
    }

    @JsonProperty("goalsFor")
    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    @JsonProperty("goalsAgainst")
    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    @JsonProperty("goalsAgainst")
    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @JsonProperty("goaldifference")
    public String getGoaldifference() {
        return goaldifference;
    }

    @JsonProperty("goaldifference")
    public void setGoaldifference(String goaldifference) {
        this.goaldifference = goaldifference;
    }

    @JsonProperty("venueId")
    public String getVenueId() {
        return venueId;
    }

    @JsonProperty("venueId")
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    @JsonProperty("venueName")
    public String getVenueName() {
        return venueName;
    }

    @JsonProperty("venueName")
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @JsonProperty("minimumAttendance")
    public Integer getMinimumAttendance() {
        return minimumAttendance;
    }

    @JsonProperty("minimumAttendance")
    public void setMinimumAttendance(Integer minimumAttendance) {
        this.minimumAttendance = minimumAttendance;
    }

    @JsonProperty("maximumAttendance")
    public Integer getMaximumAttendance() {
        return maximumAttendance;
    }

    @JsonProperty("maximumAttendance")
    public void setMaximumAttendance(Integer maximumAttendance) {
        this.maximumAttendance = maximumAttendance;
    }

    @JsonProperty("averageAttendance")
    public Integer getAverageAttendance() {
        return averageAttendance;
    }

    @JsonProperty("averageAttendance")
    public void setAverageAttendance(Integer averageAttendance) {
        this.averageAttendance = averageAttendance;
    }

    @JsonProperty("capacity")
    public Integer getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("percentSold")
    public String getPercentSold() {
        return percentSold;
    }

    @JsonProperty("percentSold")
    public void setPercentSold(String percentSold) {
        this.percentSold = percentSold;
    }

    @JsonProperty("goals0")
    public Integer getGoals0() {
        return goals0;
    }

    @JsonProperty("goals0")
    public void setGoals0(Integer goals0) {
        this.goals0 = goals0;
    }

    @JsonProperty("goals1")
    public Integer getGoals1() {
        return goals1;
    }

    @JsonProperty("goals1")
    public void setGoals1(Integer goals1) {
        this.goals1 = goals1;
    }

    @JsonProperty("goals2")
    public Integer getGoals2() {
        return goals2;
    }

    @JsonProperty("goals2")
    public void setGoals2(Integer goals2) {
        this.goals2 = goals2;
    }

    @JsonProperty("goals3")
    public Integer getGoals3() {
        return goals3;
    }

    @JsonProperty("goals3")
    public void setGoals3(Integer goals3) {
        this.goals3 = goals3;
    }

    @JsonProperty("goals4")
    public Integer getGoals4() {
        return goals4;
    }

    @JsonProperty("goals4")
    public void setGoals4(Integer goals4) {
        this.goals4 = goals4;
    }

    @JsonProperty("goals5")
    public Integer getGoals5() {
        return goals5;
    }

    @JsonProperty("goals5")
    public void setGoals5(Integer goals5) {
        this.goals5 = goals5;
    }

    @JsonProperty("goals6")
    public Integer getGoals6() {
        return goals6;
    }

    @JsonProperty("goals6")
    public void setGoals6(Integer goals6) {
        this.goals6 = goals6;
    }

    @JsonProperty("goals7")
    public Integer getGoals7() {
        return goals7;
    }

    @JsonProperty("goals7")
    public void setGoals7(Integer goals7) {
        this.goals7 = goals7;
    }

    @JsonProperty("goalsMoreThan7")
    public Integer getGoalsMoreThan7() {
        return goalsMoreThan7;
    }

    @JsonProperty("goalsMoreThan7")
    public void setGoalsMoreThan7(Integer goalsMoreThan7) {
        this.goalsMoreThan7 = goalsMoreThan7;
    }

    @JsonProperty("goalsAverage")
    public String getGoalsAverage() {
        return goalsAverage;
    }

    @JsonProperty("goalsAverage")
    public void setGoalsAverage(String goalsAverage) {
        this.goalsAverage = goalsAverage;
    }

}
