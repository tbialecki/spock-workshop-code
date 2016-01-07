package exercise

import spock.lang.Specification

class CodeMakerTest extends Specification {
    Workshop workshop = Mock()

    CodeMaker geek = new CodeMaker("Fred", workshop)

    def "tweets the correct message"() {
        when:
            geek.tweetAboutWorkshop("message");
        then:
            1 * workshop.tweet(_, "message");
    }

    def "tweets only about himself"() {
        when:
            geek.tweetAboutWorkshop("message");
        then:
            1 * workshop.tweet(geek, _);
    }

    def "stays calm when a tweet blows up"() {
        setup:
            workshop.tweet(*_) >> { throw new TweetException() }
        when:
            geek.tweetAboutWorkshop("message");
        then:
            true;
    }

    def "registers a buddy when asked to invites him"() {
        given:
            CodeMaker bob = new CodeMaker("Bob", workshop);
        when:
            geek.invite(bob);
        then:
            1 * workshop.register(bob);
    }

    def "only registers a buddy if the workshop isn't booked out"() {
        given:
            workshop.isFullyBooked() >> true;
            CodeMaker bob = new CodeMaker("Bob", workshop);
        when:
            geek.invite(bob);
        then:
            0 * workshop.register(bob);
    }

    def "bulk-registers buddies in the order they get invited"() {
        given:
            CodeMaker bob = new CodeMaker("Bob", workshop);
            CodeMaker john = new CodeMaker("John", workshop);
        when:
            geek.bulkInvite([bob, john]);
        then:
            1 * workshop.register(bob);
            1 * workshop.register(john);
    };

    def "gets angry when asked to invite a buddy who's already registered"() {
        given:
            CodeMaker bob = new CodeMaker("Bob", workshop);
            with(workshop) {
                isFullyBooked() >> false
                isRegistered(bob) >>> [false, true];
            }
        when:
            geek.invite(bob);
            geek.invite(bob);
        then:
            1 * workshop.register(bob);
            thrown(FoulBuddyException);
    }

    def "honestly reports his score"() {
        given:
            String solution = 'S1';
            workshop.getHighScores() >> [new HighScore(id: 1, geek: geek, score: 5), new HighScore(id: 5, geek: geek, score: 4)];
            workshop.submitExercise(geek, solution) >> 5;
        expect:
            geek.submitExercise(solution) == "I've scored 4 points";

    }

    def "gets excited when he cracks the highscore"() {
        given:
            String solution = 'S1';
            workshop.getHighScores() >> [new HighScore(id: 1, geek: geek, score: 5), new HighScore(id: 5, geek: geek, score: 4)];
            workshop.submitExercise(geek, solution) >> 1;
        expect:
            geek.submitExercise(solution) == "I've cracked the highscore!";
    }
}