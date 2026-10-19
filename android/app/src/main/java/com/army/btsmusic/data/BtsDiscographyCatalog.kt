package com.army.btsmusic.data

import com.army.btsmusic.model.LyricLine
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track

object BtsDiscographyCatalog {

    val sampleTracks: List<Track> = listOf(
        Track(
            id = "bts-spring-day",
            title = "Spring Day (봄날)",
            artist = "BTS",
            member = Member.OT7,
            album = "YOU NEVER WALK ALONE",
            durationSeconds = 274,
            streamUrl = "https://actions.google.com/sounds/v1/ambiences/outdoor_evening_crickets.ogg",
            youtubeVideoId = "xEeFrLSkMm8",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/2/21/BTS_-_You_Never_Walk_Alone.jpg",
            releaseYear = 2017,
            playCount = 189200420L,
            lyrics = listOf(
                LyricLine(0L, "보고 싶다", "Bogo sipda", "I miss you", true, "KIM NAMJOON!"),
                LyricLine(4200L, "이렇게 말하니까 더 보고 싶다", "Ireoke malhanikka deo bogo sipda", "Saying this makes me miss you even more", false),
                LyricLine(9100L, "너희 사진을 보고 있어도", "Neohui sajineul bogo isseodo", "Even looking at your photos", false),
                LyricLine(13500L, "보고 싶다", "Bogo sipda", "I miss you", true, "KIM SEOKJIN!"),
                LyricLine(18000L, "야속한 시간", "Yasokhan sigan", "Resentful time", false),
                LyricLine(22000L, "우린 눈꽃이 되어", "Urin nunkkochi doeeo", "Like tiny dust floating in the air", true, "MIN YOONGI!"),
                LyricLine(27500L, "하늘을 떠다녀", "Haneureul tteodanyeo", "Flying through the winter sky", true, "JUNG HOSEOK!"),
                LyricLine(32000L, "보고 싶다 보고 싶다", "Bogo sipda bogo sipda", "I miss you, I miss you", true, "PARK JIMIN!"),
                LyricLine(38000L, "얼마나 기다려야", "Eolmana gidaryeoya", "How much longer do I have to wait?", true, "KIM TAEHYUNG!"),
                LyricLine(43000L, "또 몇 밤을 더 새워야", "Tto myeot bameul deo saewoya", "How many sleepless nights do I have to pass?", true, "JEON JUNGKOOK!"),
                LyricLine(48000L, "널 보게 될까", "Neol boge doelkka", "Until I get to see you?", true, "BTS!"),
                LyricLine(53000L, "만나게 될까", "Mannage doelkka", "Until I get to meet you?", true, "BORAHAE!"),
                LyricLine(59000L, "추운 겨울 끝을 지나", "Chuun gyeoul kkeuteul jina", "Past the end of this cold winter", false),
                LyricLine(65000L, "다시 봄날이 올 때까지", "Dasi bomnari ol ttaekkaji", "Until the spring day comes again", false),
                LyricLine(71000L, "꽃 피울 때까지", "Kkot piul ttaekkaji", "Until the flowers bloom", false),
                LyricLine(76000L, "그곳에 좀 더 머물러줘", "Geugose jom deo meomulleojwo", "Please stay there a little longer", false)
            )
        ),
        Track(
            id = "bts-blood-sweat-tears",
            title = "Blood Sweat & Tears (피 땀 눈물)",
            artist = "BTS",
            member = Member.OT7,
            album = "WINGS",
            durationSeconds = 217,
            streamUrl = "https://actions.google.com/sounds/v1/ambiences/rain_heavy.ogg",
            youtubeVideoId = "hmE9f-TEutc",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/90/BTS_Wings_cover.jpg",
            releaseYear = 2016,
            playCount = 245100900L,
            lyrics = listOf(
                LyricLine(0L, "내 피 땀 눈물", "Nae pi ttam nunmul", "My blood, sweat and tears", true, "BANG! TAN!"),
                LyricLine(4500L, "내 마지막 춤을", "Nae majimak chumeul", "My last dance", false),
                LyricLine(8200L, "다 가져가 가", "Da gajyeoga ga", "Take it all away", true, "PI TTAM NUNMUL!"),
                LyricLine(12500L, "내 차가운 숨을", "Nae chagaun sumeul", "My cold breath", false),
                LyricLine(16000L, "다 가져가 가", "Da gajyeoga ga", "Take it all away", false),
                LyricLine(20500L, "원해 많이 많이 많이", "Wonhae mani mani mani", "I want it, a lot, a lot, a lot", true, "MANI MANI!"),
                LyricLine(26000L, "너의 날개는 악마의 것", "Neoui nalgaeneun akmaui geot", "Your wings are those of the devil", false),
                LyricLine(31000L, "너의 그 sweet한 향기엔", "Neoui geu sweet-han hyang-gien", "In your sweet fragrance", false)
            )
        ),
        Track(
            id = "bts-butter",
            title = "Butter",
            artist = "BTS",
            member = Member.OT7,
            album = "Butter",
            durationSeconds = 164,
            streamUrl = "https://actions.google.com/sounds/v1/household/clock_ticking_fast.ogg",
            youtubeVideoId = "WMweEpGlu_U",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/d/db/BTS_-_Butter.png",
            releaseYear = 2021,
            playCount = 412880000L,
            lyrics = listOf(
                LyricLine(0L, "Smooth like butter, like a criminal undercover", "Smooth like butter, like a criminal undercover", "Smooth like butter, like a criminal undercover", true, "HEY!"),
                LyricLine(4500L, "Gon' pop like trouble breaking into your heart like that", "Gon' pop like trouble breaking into your heart like that", "Gon' pop like trouble breaking into your heart like that", false),
                LyricLine(9200L, "Cool shade, stunner, yeah, I owe it all to my mother", "Cool shade, stunner, yeah, I owe it all to my mother", "Cool shade, stunner, yeah, I owe it all to my mother", false),
                LyricLine(14000L, "Hot like summer, yeah, I'm making you sweat like that", "Hot like summer, yeah, I'm making you sweat like that", "Hot like summer, yeah, I'm making you sweat like that", true, "SWEAT LIKE THAT!"),
                LyricLine(19000L, "Break it down!", "Break it down!", "Break it down!", true, "BTS!"),
                LyricLine(24000L, "Side step, right, left, to my beat", "Side step, right, left, to my beat", "Side step, right, left, to my beat", true, "RIGHT! LEFT!"),
                LyricLine(28500L, "High like the moon, rock with me, baby", "High like the moon, rock with me, baby", "High like the moon, rock with me, baby", false)
            )
        ),
        Track(
            id = "bts-dynamite",
            title = "Dynamite",
            artist = "BTS",
            member = Member.OT7,
            album = "BE",
            durationSeconds = 199,
            streamUrl = "https://actions.google.com/sounds/v1/science_fiction/teleport.ogg",
            youtubeVideoId = "gdZLi9oWNZg",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/0/05/BTS_-_Dynamite_%28single_cover%29.png",
            releaseYear = 2020,
            playCount = 590120300L,
            lyrics = listOf(
                LyricLine(0L, "'Cause I-I-I'm in the stars tonight", "'Cause I-I-I'm in the stars tonight", "'Cause I-I-I'm in the stars tonight", true, "BANGTAN!"),
                LyricLine(5000L, "So watch me bring the fire and set the night alight", "So watch me bring the fire and set the night alight", "So watch me bring the fire and set the night alight", false),
                LyricLine(10500L, "Shoes on, get up in the morn'", "Shoes on, get up in the morn'", "Shoes on, get up in the morn'", false),
                LyricLine(14000L, "Cup of milk, let's rock and roll", "Cup of milk, let's rock and roll", "Cup of milk, let's rock and roll", false),
                LyricLine(18000L, "King Kong, kick the drum, rolling on like a Rolling Stone", "King Kong, kick the drum, rolling on like a Rolling Stone", "King Kong, kick the drum, rolling on like a Rolling Stone", false),
                LyricLine(23000L, "Sing song when I'm walking home, jump up to the top, LeBron", "Sing song when I'm walking home, jump up to the top, LeBron", "Sing song when I'm walking home, jump up to the top, LeBron", false),
                LyricLine(28000L, "Light it up like dynamite, whoa", "Light it up like dynamite, whoa", "Light it up like dynamite, whoa", true, "DYNAMITE!")
            )
        ),
        Track(
            id = "bts-on",
            title = "ON",
            artist = "BTS",
            member = Member.OT7,
            album = "MAP OF THE SOUL : 7",
            durationSeconds = 246,
            streamUrl = "https://actions.google.com/sounds/v1/sports/stadium_crowd_cheer.ogg",
            youtubeVideoId = "mPVDGOVjRQ0",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/2/21/BTS_-_Map_of_the_Soul_7.png",
            releaseYear = 2020,
            playCount = 485000000L,
            lyrics = listOf(
                LyricLine(0L, "I can't understand what people are sayin'", "I can't understand what people are sayin'", "I can't understand what people are sayin'", true, "BTS!"),
                LyricLine(5000L, "어느 장단에 맞춰야 될지", "Eoneu jangdane matchwoya doelji", "Which beat should I march to?", false),
                LyricLine(10000L, "한 발자국 떼면 커지는 그림자", "Han baljaguk ttemyeon keojineun geurimja", "One step forward and the shadow grows", false),
                LyricLine(15000L, "Bring it, bring the pain on, yeah", "Bring it, bring the pain on, yeah", "Bring it on!", true, "BRING THE PAIN!")
            )
        ),
        Track(
            id = "bts-idol",
            title = "IDOL",
            artist = "BTS",
            member = Member.OT7,
            album = "LOVE YOURSELF 結 'Answer'",
            durationSeconds = 223,
            streamUrl = "https://actions.google.com/sounds/v1/household/applause_medium.ogg",
            youtubeVideoId = "pBuZEGYXA6E",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/9e/BTS_-_Love_Yourself_Answer_album_cover.jpg",
            releaseYear = 2018,
            playCount = 492000000L,
            lyrics = listOf(
                LyricLine(0L, "You can call me artist, you can call me idol", "You can call me artist, you can call me idol", "You can call me artist, you can call me idol", true, "IDOL!"),
                LyricLine(5000L, "아님 어떤 다른 뭐라 해도", "Anim eotteon dareun mwora haedo", "Or whatever else you want to call me", false),
                LyricLine(10000L, "I don't care, I'm proud of it", "I don't care, I'm proud of it", "I'm proud of it", true, "OH OH U OH!"),
                LyricLine(15000L, "얼쑤 좋다! 지화자 좋다!", "Eolssu jota! Jihwaja jota!", "Let's go! Feel the groove!", true, "JIKIMI!")
            )
        ),
        Track(
            id = "bts-run-bts",
            title = "Run BTS (달려라 방탄)",
            artist = "BTS",
            member = Member.OT7,
            album = "Proof",
            durationSeconds = 205,
            streamUrl = "https://actions.google.com/sounds/v1/ambiences/car_interior_driving.ogg",
            youtubeVideoId = "bO28Y6V9t64",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/a/a2/BTS_-_Proof_album_cover.png",
            releaseYear = 2022,
            playCount = 345000000L,
            lyrics = listOf(
                LyricLine(0L, "논현동 열일곱, 어디 갈 지 몰라", "Nonhyeon-dong yeorilgop, eodi gal ji molla", "Nonhyeon-dong, seventeen, not knowing where to go", true, "RUN BTS!"),
                LyricLine(5000L, "맨 땅에 헤딩하듯 뛰었지", "Maen ttange hedinghadeut ttwieotji", "We ran like throwing ourselves onto the bare ground", false),
                LyricLine(10000L, "달려라 방탄, 달리기 시작해", "Dallyeora bangtan, dalligi sijakhae", "Run BTS, start sprinting!", true, "RUN BULLETPROOF RUN!")
            )
        ),
        Track(
            id = "bts-fake-love",
            title = "Fake Love",
            artist = "BTS",
            member = Member.OT7,
            album = "Love Yourself 轉 'Tear'",
            durationSeconds = 242,
            streamUrl = "https://actions.google.com/sounds/v1/household/fireplace.ogg",
            youtubeVideoId = "7C2z4GqqS5E",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/9e/BTS_-_Love_Yourself_Answer_album_cover.jpg",
            releaseYear = 2018,
            playCount = 680000000L,
            lyrics = listOf(
                LyricLine(0L, "널 위해서라면 난 슬퍼도 기쁜 척 할 수가 있었어", "Neol wihaeseoramyeon nan seulpeodo gippeun cheok hal suga isseosseo", "For you, even when I'm sad, I can pretend to be happy", true, "BTS!"),
                LyricLine(6000L, "널 위해서라면 난 아파도 강한 척 할 수가 있었어", "Neol wihaeseoramyeon nan apado ganghan cheok hal suga isseosseo", "For you, even when it hurts, I could pretend to be strong", false),
                LyricLine(12000L, "Love you so bad, love you so bad", "Love you so bad, love you so bad", "Love you so bad", true, "LOVE YOU SO BAD!"),
                LyricLine(18000L, "I'm so sick of this fake love", "I'm so sick of this fake love", "I'm so sick of this fake love", true, "FAKE LOVE!")
            )
        ),
        Track(
            id = "bts-life-goes-on",
            title = "Life Goes On",
            artist = "BTS",
            member = Member.OT7,
            album = "BE",
            durationSeconds = 207,
            streamUrl = "https://actions.google.com/sounds/v1/water/lapping_waves.ogg",
            youtubeVideoId = "-5q5mZbe3V8",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/0/05/BTS_-_Dynamite_%28single_cover%29.png",
            releaseYear = 2020,
            playCount = 420000000L,
            lyrics = listOf(
                LyricLine(0L, "어느 날 세상이 멈췄어", "Eoneu nal sesangi meomchwosseo", "One day, the world stopped", true, "BTS!"),
                LyricLine(5000L, "아무런 예고도 하나 없이", "Amureon yegodo hana eopsi", "Without any forewarning", false),
                LyricLine(11000L, "Like an echo in the forest", "Like an echo in the forest", "Like an echo in the forest", false),
                LyricLine(16000L, "Yeah life goes on, like an arrow in the blue sky", "Yeah life goes on, like an arrow in the blue sky", "Yeah life goes on", true, "LIFE GOES ON!")
            )
        ),
        Track(
            id = "bts-mic-drop",
            title = "MIC Drop",
            artist = "BTS",
            member = Member.OT7,
            album = "Love Yourself 承 'Her'",
            durationSeconds = 238,
            streamUrl = "https://actions.google.com/sounds/v1/sports/stadium_crowd_cheer.ogg",
            youtubeVideoId = "kTlv5_Bs8aw",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/9e/BTS_-_Love_Yourself_Answer_album_cover.jpg",
            releaseYear = 2017,
            playCount = 590000000L,
            lyrics = listOf(
                LyricLine(0L, "Yeah 누가 내 수저 더럽대", "Yeah nuga nae sujeo deoreopdae", "Yeah, who says my spoon is dirty?", true, "MIC DROP!"),
                LyricLine(5000L, "I don't care 마이크 잡음 금수저 여럿 패", "I don't care maikeu jabeum geumsujeo yeoreot pae", "I don't care, when I grab the mic I beat those golden spoons", false),
                LyricLine(10000L, "Did you see my bag? Did you see my bag?", "Did you see my bag? Did you see my bag?", "Did you see my bag?", true, "MY BAG!"),
                LyricLine(15000L, "트로피들로 백이 넘쳐흘러", "Teuropideullo baegi neomchyeoheulleo", "It's hella trophies and it's hella thick", true, "HELLA THICK!")
            )
        ),
        Track(
            id = "jk-standing-next-to-you",
            title = "Standing Next to You",
            artist = "Jung Kook",
            member = Member.JUNG_KOOK,
            album = "GOLDEN",
            durationSeconds = 206,
            streamUrl = "https://actions.google.com/sounds/v1/ambiences/carnival_calliope.ogg",
            youtubeVideoId = "UNo0TGZuv4w",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/9/90/Jungkook_-_Golden.png",
            releaseYear = 2023,
            playCount = 312000000L,
            lyrics = listOf(
                LyricLine(0L, "Play me slow, push the button, let's go", "Play me slow, push the button, let's go", "Play me slow, push the button, let's go", false),
                LyricLine(5000L, "Standing next to you", "Standing next to you", "Standing next to you", true, "JEON JUNGKOOK!"),
                LyricLine(9500L, "Fire, screaming, crying, you don't hear a sound", "Fire, screaming, crying, you don't hear a sound", "Fire, screaming, crying, you don't hear a sound", false),
                LyricLine(15000L, "No, we cannot be divided, through the night", "No, we cannot be divided, through the night", "No, we cannot be divided, through the night", false),
                LyricLine(21000L, "Standing next to you", "Standing next to you", "Standing next to you", true, "GOLDEN MAKNAE!")
            )
        ),
        Track(
            id = "jimin-like-crazy",
            title = "Like Crazy",
            artist = "Jimin",
            member = Member.JIMIN,
            album = "FACE",
            durationSeconds = 212,
            streamUrl = "https://actions.google.com/sounds/v1/water/lapping_waves.ogg",
            youtubeVideoId = "qcZ7e9EOkeU",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/4/4c/Jimin_-_Face.png",
            releaseYear = 2023,
            playCount = 284500000L,
            lyrics = listOf(
                LyricLine(0L, "She's saying: Baby, 생각하지 마", "She's saying: Baby, saenggakhaji ma", "She's saying: Baby, don't think about it", false),
                LyricLine(5500L, "이 밤의 끝엔 아무것도 없어", "I bamu kkeuten amugeotdo eopseo", "There is nothing at the end of this night", false),
                LyricLine(11000L, "날 어지럽게 해", "Nal eojireopge hae", "Make me dizzy", true, "PARK JIMIN!"),
                LyricLine(16000L, "I'd rather be lost in the lights", "I'd rather be lost in the lights", "I'd rather be lost in the lights", true, "LOST IN THE LIGHTS!"),
                LyricLine(22000L, "그림자 속의 춤을 춰", "Geurimja sogui chumeul chwo", "Dancing in the shadows", false)
            )
        ),
        Track(
            id = "suga-haegeum",
            title = "Haegeum (해금)",
            artist = "Agust D / SUGA",
            member = Member.SUGA,
            album = "D-DAY",
            durationSeconds = 168,
            streamUrl = "https://actions.google.com/sounds/v1/weapons/laser_gun.ogg",
            youtubeVideoId = "iy9qZR_OGa0",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/0/0f/Agust_D_-_D-Day.png",
            releaseYear = 2023,
            playCount = 198000000L,
            lyrics = listOf(
                LyricLine(0L, "이 곡은 금지된 것을 해금하는 곡", "I gogeun geumjidoen geoseul haegeumhaneun gok", "This song unlocks what was prohibited", true, "AGUST D!"),
                LyricLine(4800L, "자유의 역설, 또 다른 억압", "Jayuui yeokseol, tto dareun eogap", "The paradox of freedom, another constraint", false),
                LyricLine(9500L, "해금, 또 다른 해금", "Haegeum, tto dareun haegeum", "Haegeum, yet another liberation", true, "MIN YOONGI!"),
                LyricLine(14000L, "세상은 너무 많은 정보로 넘쳐나", "Sesangeun neomu maneun jeongboro neomchyeona", "The world is drowning in excess information", false)
            )
        ),
        Track(
            id = "rm-wild-flower",
            title = "Wild Flower (들꽃놀이 with youjeen)",
            artist = "RM",
            member = Member.RM,
            album = "Indigo",
            durationSeconds = 273,
            streamUrl = "https://actions.google.com/sounds/v1/weather/wind_blowing.ogg",
            youtubeVideoId = "u18be_K6PKE",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/4/4e/RM_-_Indigo.png",
            releaseYear = 2022,
            playCount = 145000000L,
            lyrics = listOf(
                LyricLine(0L, "화려한 불꽃보다는 들꽃으로 살고파", "Hwaryeohan bulkkotbodaneun deulkkocheuro salgopa", "Rather than a flashy firework, I wanna live as a wildflower", true, "KIM NAMJOON!"),
                LyricLine(6000L, "하늘에서 땅으로 내려와", "Haneureseo ttangeuro naeryeowa", "Coming down from the sky to the earth", false),
                LyricLine(12000L, "Light a flower, flowerwork", "Light a flower, flowerwork", "Light a flower, flowerwork", true, "INDIGO!")
            )
        ),
        Track(
            id = "jin-the-astronaut",
            title = "The Astronaut",
            artist = "Jin",
            member = Member.JIN,
            album = "The Astronaut",
            durationSeconds = 282,
            streamUrl = "https://actions.google.com/sounds/v1/science_fiction/alien_atmosphere.ogg",
            youtubeVideoId = "c6ASQOwKkhk",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/c/cb/Jin_-_The_Astronaut.png",
            releaseYear = 2022,
            playCount = 176000000L,
            lyrics = listOf(
                LyricLine(0L, "우주를 건너 너에게로 갈게", "Ujureul geonneo neoege-ro galge", "I will cross the universe to get to you", true, "WORLDWIDE HANDSOME!"),
                LyricLine(6000L, "When I'm with you, there is no one else", "When I'm with you, there is no one else", "When I'm with you, there is no one else", false),
                LyricLine(13000L, "I get heaven to myself", "I get heaven to myself", "I get heaven to myself", true, "KIM SEOKJIN!")
            )
        ),
        Track(
            id = "jhope-arson",
            title = "Arson (방화)",
            artist = "j-hope",
            member = Member.J_HOPE,
            album = "Jack In The Box",
            durationSeconds = 159,
            streamUrl = "https://actions.google.com/sounds/v1/emergency/siren_wail.ogg",
            youtubeVideoId = "QmpTkkaKYSU",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/2/20/J-Hope_-_Jack_in_the_Box.png",
            releaseYear = 2022,
            playCount = 135000000L,
            lyrics = listOf(
                LyricLine(0L, "Let's burn, it's done", "Let's burn, it's done", "Let's burn, it's done", true, "J-HOPE!"),
                LyricLine(4500L, "내가 불을 질렀어", "Naega bureul jilleosseo", "I set the fire", false),
                LyricLine(9000L, "이제 질문을 던져: 불을 끌 것인가?", "Ije jilmuneul deonjyeo: bureul kkeul geosinga?", "Now I ask the question: Will I put out the fire?", true, "JACK IN THE BOX!")
            )
        ),
        Track(
            id = "v-love-me-again",
            title = "Love Me Again",
            artist = "V",
            member = Member.V,
            album = "Layover",
            durationSeconds = 198,
            streamUrl = "https://actions.google.com/sounds/v1/household/fireplace.ogg",
            youtubeVideoId = "HYzyRHAHJl8",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/0/0e/V_-_Layover.png",
            releaseYear = 2023,
            playCount = 230000000L,
            lyrics = listOf(
                LyricLine(0L, "넌 날 잊었겠지만, 난 아직 그대로야", "Neon nal ijeotgetjiman, nan ajik geudaeroya", "You might have forgotten me, but I'm still the same", true, "KIM TAEHYUNG!"),
                LyricLine(6000L, "I wish you would love me again", "I wish you would love me again", "I wish you would love me again", true, "LOVE ME AGAIN!"),
                LyricLine(12000L, "No, I don't want nobody else", "No, I don't want nobody else", "No, I don't want nobody else", false)
            )
        ),
        Track(
            id = "bts-mikrokosmos",
            title = "Mikrokosmos (소우주)",
            artist = "BTS",
            member = Member.OT7,
            album = "MAP OF THE SOUL : PERSONA",
            durationSeconds = 224,
            streamUrl = "https://actions.google.com/sounds/v1/science_fiction/alien_atmosphere.ogg",
            youtubeVideoId = "x-AeaB5_6-E",
            coverUrl = "https://upload.wikimedia.org/wikipedia/en/7/7b/BTS_-_Map_of_the_Soul_Persona.png",
            releaseYear = 2019,
            playCount = 310000000L,
            lyrics = listOf(
                LyricLine(0L, "반짝이는 별빛들", "Banjjagineun byeolbitdeul", "Twinkling starlights", false),
                LyricLine(4000L, "깜빡이는 불 켜진 건물", "Kkamppagineun bul kyeojin geonmul", "Buildings with blinking lights", false),
                LyricLine(8500L, "우린 빛나고 있네", "Urin binnago inne", "We are shining", true, "ARMY!"),
                LyricLine(13000L, "각자의 방, 각자의 별에서", "Gakjaui bang, gakjaui byeoreseo", "In each of our rooms, on our own stars", false),
                LyricLine(18000L, "You got me, I dream looking at you", "You got me, I dream looking at you", "You got me, I dream looking at you", true, "BORAHAE!"),
                LyricLine(24000L, "I got you, 칠흑 같던 밤들 속", "I got you, chilhuk gatdeon bamdeul sok", "I got you, in those pitch-black nights", false),
                LyricLine(30000L, "서로가 본 서로의 빛", "Seoroga bon seoroui bit", "The lights we saw in each other", true, "BTS & ARMY!")
            )
        )
    )

    fun getTracksForMember(member: Member): List<Track> {
        return if (member == Member.OT7) sampleTracks else sampleTracks.filter { it.member == member }
    }
}
