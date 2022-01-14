import streamlit as st
from transformers import AutoTokenizer
import torch
from sumy.parsers.plaintext import PlaintextParser
from sumy.nlp.tokenizers import Tokenizer
from sumy.summarizers.text_rank import TextRankSummarizer
from sumy.summarizers.lex_rank import LexRankSummarizer

filename = "vanilla_mt5.pth"
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

@st.cache(allow_output_mutation=True)
def load_tokenizer():
    tokenizer = AutoTokenizer.from_pretrained("csebuetnlp/mT5_multilingual_XLSum")
    return tokenizer

@st.cache(allow_output_mutation=True)
def load_model():
    model = torch.load(filename, map_location=device)
    return model

def summarizeText(text, tokenizer, model, B=4):
    text_encoding = tokenizer(
        text,
        max_length=250,
        padding='max_length',
        truncation=True,
        return_attention_mask=True,
        add_special_tokens=True,
        return_tensors='pt'
    )
    generated_ids = model.generate(
        input_ids=text_encoding['input_ids'].to(device),
        attention_mask=text_encoding['attention_mask'].to(device),
        max_length=15,
        num_beams=B,
        repetition_penalty=2.5,
        length_penalty=1.0,
        early_stopping=True
    )

    preds = [
            tokenizer.decode(gen_id, skip_special_tokens=True, clean_up_tokenization_spaces=True)
            for gen_id in generated_ids
    ]
    return "".join(preds)

def summarizeRank(article,num_sentences=3,tokenizer= Tokenizer('arabic'),summarizer=TextRankSummarizer()):
  parser = PlaintextParser.from_string(article,tokenizer)
  summary = summarizer(parser.document,num_sentences)

  out_summary = ""
  for sentence in summary:
      out_summary+= str(sentence) + " "
  return out_summary
