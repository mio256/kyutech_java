# MyCalc

1. get `DEEPL_KEY` and `OPENAI_KEY`
2. `mv .env.example .env`
3. set these in `.env`
4. `export $(cat .env | grep -v ^#)  `
5. `mvn exec:java`
