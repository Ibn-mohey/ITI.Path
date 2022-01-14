import streamlit as st
import base64
import utiles
from sumy.summarizers.text_rank import TextRankSummarizer
from sumy.summarizers.lex_rank import LexRankSummarizer

def main():
    st.title("Summarize")
    st.markdown(
        f"""
            <style>
                .reportview-container .main .block-container{{
                    max-width: 1200px;
                    padding-top: 3rem;
                    padding-right: 1rem;
                    padding-left: 1rem;
                    padding-bottom: 5rem;
                }}
            </style>
            """,
        unsafe_allow_html=True,
    )
    post = st.text_area("",placeholder="Type Here", height=200)
    mechanism = st.radio(
        "Choose the mechanism : ",
        ('Text Rank', 'Lex Rank', 'Machine learning'))
    if mechanism == 'Text Rank' or mechanism == 'Lex Rank':
        num_sentences = st.number_input('How many sentences?', min_value=1, max_value=10, value=3, step=1)
    else:
        B = st.number_input('B value', min_value=1, max_value=10, value=4, step=1)
    result = ""
    if st.button("Summarize"):
        if mechanism == 'Machine learning':
            model = utiles.load_model()
            tokenizer = utiles.load_tokenizer()
            result = utiles.summarizeText(post, tokenizer, model, B=B)
        elif(mechanism == 'Text Rank'):
            result = utiles.summarizeRank(post, summarizer=TextRankSummarizer(), num_sentences=num_sentences)
        elif(mechanism == 'Lex Rank'):
            result = utiles.summarizeRank(post, summarizer=LexRankSummarizer(), num_sentences=num_sentences)
        st.write(result)


if __name__=='__main__':
    main()
