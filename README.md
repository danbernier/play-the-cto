# play-the-cto

I made a CLI game where you get to...Play the CTO!

## Origin Story

Way back when I was a young programmer working at a MegaCorp, I was often
frustrated by the lack of commitment to improving the infrastructure of the
software we built. Under the direction of senior players from the business side,
we'd pile on features, and watch the software grow brittle and unstable.

To vent my frustration, I thought I'd make a game that lets you build an
unspecific subscription-based "IT resource," where your only choice is: should
I invest in features, or infrastructure? Because I had few graphics skills, I
made it a CLI.

If you invest in infrastructure, you reap several benefits:
* the system stabilizes: the risk of crashes goes down
* new features are cheaper to add

To not be so biased, I made it tempting to add features:
* you attract many more users by adding features, and users give you dollars
* particularly, new users pay more: recurring users get an "upgrade" discount (this was ~2005, after all)

By luck, the game ended up being balanced just enough that it was kind of fun to play.

There are loads of updates I could make to the gameplay, but I kind of want the
code to stay as it is. It's much more useful as historical record than as
playable software.

## Today

I held on to the source, but never published it anywhere. I'm tired of this
folder sitting, unprotected, in my "projects" folder, so I'm putting it here on
github.

While I was at it, I thought it might be fun to port it to Ruby, as an exercise
in seeing how far my software thinking has come in the last decade. I'll
publish it as a gem, because that's got to be the easiest way to let people
play it at this point.

This also gives me the chance, if I decide it's worth it, to update the gameplay.
And BTW, the CLI as a UI is _terrible,_ so I might update that, too. We'll see.
